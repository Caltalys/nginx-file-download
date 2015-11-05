package com.example.webgui.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webapi.RouteDefine;

@Controller
public class AdminWebGuiSdCardOrderController {
	
	static final String LIST = "admin/sd_card_order_list";

	@RequestMapping(value = RouteDefine.ADMIN_SD_CARD_ORDERS, method = RequestMethod.GET)
	public String list() {
		return LIST;
	}

}
