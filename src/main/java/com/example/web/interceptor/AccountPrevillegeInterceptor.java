package com.example.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.common.HttpResponseTool;
import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;
import com.example.web.consts.RouteDefine;

public class AccountPrevillegeInterceptor extends HandlerInterceptorAdapter {

	private AccountRMapper accountRMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		for (String e : LoginInterceptor.unrestrictedRoutePatterns) {
			if (uri.matches(e)) {
				return true;
			}
		}
		Long id = LoginInterceptor.getAccountId(request);
		if (id == null) {
			return false;
		}
		
		return checkAccount(response, uri, id);
	}
	
	private boolean checkAccount(HttpServletResponse response, String uri, long id) {
		Account account = accountRMapper.selectById(id);
		if (!Account.isValidAccount(account)) {
			return false;
		}
		if (uri.startsWith(RouteDefine.ADMIN) && !Account.isAdmin(account)) {
			HttpResponseTool.setStatusAsUnauthorized(response);
			return false;
		}
		return true;
	}

	public AccountRMapper getAccountRMapper() {
		return accountRMapper;
	}

	public void setAccountRMapper(AccountRMapper accountRMapper) {
		this.accountRMapper = accountRMapper;
	}

}
