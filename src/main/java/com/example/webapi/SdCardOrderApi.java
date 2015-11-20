package com.example.webapi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpResponseTool;
import com.example.domain.SdCardOrder;
import com.example.filter.LoginInterceptor;
import com.example.persist.must.SdCardOrderRMapper;

@RestController
public class SdCardOrderApi {

	@Autowired
	private SdCardOrderRMapper rMapper;

	@RequestMapping(value = RouteDefine.API_I_SD_CARD_ORDERS, method = RequestMethod.GET)
	public void list(HttpServletRequest request, HttpServletResponse response) {
		Long id = LoginInterceptor.getAccountId(request);
		List<SdCardOrder> list = rMapper.selectByUserId(id);
		HttpResponseTool.writeResponse(response, list);
	}

}
