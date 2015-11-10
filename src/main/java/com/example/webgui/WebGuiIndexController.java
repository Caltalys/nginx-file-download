package com.example.webgui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.Account;
import com.example.filter.LoginInterceptor;
import com.example.persist.must.AccountRMapper;

@Controller
public class WebGuiIndexController {
	
	static final String INDEX = "index";
	static final String ADMIN_INDEX = "admin/" + INDEX;

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private AccountRMapper accoutRMapper;

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		Long id = LoginInterceptor.getAccountId(request);
		Account account = accoutRMapper.selectById(id);
		if (account.getIsAdmin()) {
			return ModelAndViewTool.newModelAndView(appConfig, ADMIN_INDEX);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, INDEX);
		ret.getModel().put("ASSETS_VERSION", "v1");
		return ret;
	}

}
