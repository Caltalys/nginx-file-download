package com.example.webapi.assets;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.FilePathTool;
import com.example.common.HttpResponseTool;
import com.example.config.AppConfig;
import com.example.webapi.RouteDefine;

@RestController
public class AssetsJavascriptController {

	@Autowired
	private AppConfig appConfig;

	@RequestMapping(value = RouteDefine.ASSETS_VERSION_JAVASCRIPT + "/**", method = RequestMethod.GET)
	public void get(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String uri = request.getRequestURI();
		String path = FilePathTool.join(appConfig.getDataDir(), uri);
		File file = new File(path);
		HttpResponseTool.writeCss(response, file);
	}

}
