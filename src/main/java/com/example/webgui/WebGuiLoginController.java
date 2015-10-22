package com.example.webgui;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.Sha2Encoder;
import com.example.domain.Account;
import com.example.filter.LoginInterceptor;
import com.example.persist.mapper.AccountRMapper;

@Controller
public class WebGuiLoginController {

	private static final Logger logger = LoggerFactory
			.getLogger(WebGuiLoginController.class);

	@Autowired
	private AccountRMapper rMapper;

	@RequestMapping(value = WebGuiRouteDefine.LOGIN, method = RequestMethod.GET)
	public ModelAndView gotoLoginPage() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = WebGuiRouteDefine.LOGIN, method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (LoginInterceptor.sessionIdExist(request)) {
			response.sendRedirect("/files");
			logger.info(LoginInterceptor.getSessionId(request)
					+ " has already signed in");
			return;
		}
		String username = request.getParameter("username");
		String plain = request.getParameter("password");
		String cypher = Sha2Encoder.encode(plain);
		Account e = new Account();
		e.setName(username);
		e.setPassword(cypher);
		Account account = rMapper.selectByNameAndPassword(e);
		if (account == null) {
			response.sendRedirect("/");
			return;
		}
		LoginInterceptor.setSessionId(request, account.getId());
		logger.info(username + " signed in OK");
		response.sendRedirect("/files");
	}

	@RequestMapping(value = WebGuiRouteDefine.LOGOUT)
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (LoginInterceptor.sessionIdExist(request)) {
			LoginInterceptor.removeSessionId(request);
		}
		response.sendRedirect("/");
	}

}
