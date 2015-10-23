package com.example.webgui.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.web.RouteDefine;

@Controller
public class AdminWebGuiDownloadTaskController {

	@RequestMapping(value = RouteDefine.ADMIN_DOWNLOAD_TASKS, method = RequestMethod.GET)
	public String get() {
		return "admin/dld_task_list";
	}

}
