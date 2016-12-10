package com.example.webapi.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpResponseTool;
import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;
import com.example.persist.must.AccountWMapper;
import com.example.webapi.RouteDefine;

@RestController
public class AdminAccountApi {

	@Autowired
	private AccountRMapper rMapper;
	@Autowired
	private AccountWMapper wMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN_ACCOUNTS, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		List<Account> list = rMapper.selectAllIngoreEnabled();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_ACCOUNTS + "/{id}/enable", method = RequestMethod.POST)
	public void enable(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		Account account = rMapper.selectByIdIgnoreEnabled(id);
		if (account == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		account.enable();
		wMapper.updateEnabled(account);
		HttpResponseTool.writeResponse(response, account);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_ACCOUNTS + "/{id}/disable", method = RequestMethod.POST)
	public void disable(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		Account account = rMapper.selectByIdIgnoreEnabled(id);
		if (account == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		account.disable();
		wMapper.updateEnabled(account);
		HttpResponseTool.writeResponse(response, account);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_ACCOUNTS + "/{id}/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		Account account = rMapper.selectByIdIgnoreEnabled(id);
		if (account == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		wMapper.delete(account);
		HttpResponseTool.writeResponse(response, account);
	}

}
