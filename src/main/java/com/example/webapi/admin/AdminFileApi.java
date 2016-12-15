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
import com.example.domain.File;
import com.example.persist.must.FileRMapper;
import com.example.persist.must.FileWMapper;
import com.example.webapi.RouteDefine;

@RestController
public class AdminFileApi {

	@Autowired
	private FileRMapper rMapper;
	@Autowired
	private FileWMapper wMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN_FILES, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		List<File> list = rMapper.selectAll();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_FILES + "/{id}/actions/disable", method = RequestMethod.POST)
	public void disable(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		File e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.disable();
		wMapper.disable(e);
		HttpResponseTool.writeResponse(response, e);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_FILES + "/{id}/actions/enable", method = RequestMethod.POST)
	public void enable(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		File e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.enable();
		wMapper.enable(e);
		HttpResponseTool.writeResponse(response, e);
	}
	
	@RequestMapping(value = RouteDefine.API_ADMIN_FILES + "/{id}/actions/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		File e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		wMapper.delete(e);
		HttpResponseTool.writeResponse(response, e);
	}

}
