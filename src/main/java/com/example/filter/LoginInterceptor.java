package com.example.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;
import com.example.webapi.RouteDefine;
import com.google.common.collect.Sets;

public class LoginInterceptor extends BaseInterceptor {

	public static final String PREFIX = "example.com/";
	public static final String SEESION_ID = PREFIX + "sessionId";

	static final Set<String> unrestrictedRoutePatterns = Sets.newHashSet();

	static {
		unrestrictedRoutePatterns.add(RouteDefine.BASE_ASSETS + ".*");
		unrestrictedRoutePatterns.add(RouteDefine.LOGIN + ".*");
		unrestrictedRoutePatterns.add(RouteDefine.API + ".*");
	}

	private AccountRMapper accountRMapper;

	public static void redirectToLogin(HttpServletResponse response) throws IOException {
		response.sendRedirect(RouteDefine.LOGIN);
	}

	public static void setSessionId(HttpServletRequest request, Long userId) {
		request.getSession().setAttribute(SEESION_ID, userId);
	}

	public static Long getSessionId(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(SEESION_ID);
		Long ret = null;
		if (obj != null) {
			ret = (Long) obj;
		}
		return ret;
	}

	public static boolean sessionIdExist(HttpServletRequest request) {
		Long sessionId = getSessionId(request);
		return sessionId != null;
	}

	public static void removeSessionId(HttpServletRequest request) {
		if (!sessionIdExist(request)) {
			return;
		}
		request.getSession().removeAttribute(SEESION_ID);
	}

	public static Long getAccountId(HttpServletRequest request) {
		return getSessionId(request);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		for (String e : unrestrictedRoutePatterns) {
			if (uri.matches(e)) {
				return true;
			}
		}
		Long id = getAccountId(request);
		if (id == null) {
			redirectToLogin(response);
			return false;
		}
		Account account = accountRMapper.selectById(id);
		if (Account.isValidAccount(account)) {
			return true;
		}
		redirectToLogin(response);
		return false;
	}

	public AccountRMapper getAccountRMapper() {
		return accountRMapper;
	}

	public void setAccountRMapper(AccountRMapper accountRMapper) {
		this.accountRMapper = accountRMapper;
	}

}
