package com.example.webgui.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.webapi.RouteDefine;
import com.example.webgui.WebGuiDefine;

@Controller
public class AdminDownloadTaskController {

	static final String VIEW_NAME_PREFIX = WebGuiDefine.ADMIN + "/dld-task/";
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;

	@Autowired
	private AppConfig appConfig;

	@RequestMapping(value = RouteDefine.ADMIN_DOWNLOAD_TASKS, method = RequestMethod.GET)
	public ModelAndView get() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_LIST);
	}

}
