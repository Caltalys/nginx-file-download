package com.example.admin.webapi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpServletResponseUtil;
import com.example.domain.SdCardOrder;
import com.example.persist.rdbms.SdCardOrderRMapper;
import com.example.webapi.RouteDefine;

@RestController
public class AdminSdCardOrderController {

	@Autowired
	private SdCardOrderRMapper rMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN_SD_CARD_ORDERS, method = RequestMethod.GET)
	public void list(HttpServletRequest request, HttpServletResponse response) {
		List<SdCardOrder> list = rMapper.selectAll();
		HttpServletResponseUtil.writeResponse(response, list);
	}

}
