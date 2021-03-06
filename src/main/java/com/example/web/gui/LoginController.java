package com.example.web.gui;

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

import com.example.common.ModelAndViewTool;
import com.example.common.Sha2Encoder;
import com.example.config.AppConfig;
import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;
import com.example.web.consts.RouteDefine;
import com.example.web.interceptor.LoginInterceptor;

@Controller
public class LoginController {

	static final String PSWD = "password";
	static final String USERNAME = "username";

	static final String VIEW_NAME_LOGIN = "login";

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private AccountRMapper rMapper;

	@RequestMapping(value = RouteDefine.LOGIN, method = RequestMethod.GET)
	public ModelAndView gotoLoginPage(HttpServletRequest request) {
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_LOGIN);
	}

	@RequestMapping(value = RouteDefine.LOGIN, method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (LoginInterceptor.sessionIdExist(request)) {
			logger.info(LoginInterceptor.getSessionId(request) + " has already signed in");
			response.sendRedirect(RouteDefine.ROOT);
			return;
		}

		String username = request.getParameter(USERNAME);
		String plain = request.getParameter(PSWD);
		String cypher = Sha2Encoder.encode(plain);
		Account e = new Account();
		e.setName(username);
		e.setPassword(cypher);
		Account account = rMapper.selectEnabledByNameAndPassword(e);
		if (account == null) {
			response.sendRedirect(RouteDefine.ROOT + "login");
			return;
		}
		LoginInterceptor.initSession(request, account);
		logger.info(username + " signed in OK");
		response.sendRedirect(RouteDefine.ROOT);
	}

	@RequestMapping(value = RouteDefine.LOGOUT)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginInterceptor.destroySession(request);
		response.sendRedirect(RouteDefine.LOGIN);
	}

}
