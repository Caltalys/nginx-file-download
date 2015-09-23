package com.example.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.DownloadTask;
import com.example.domain.File;
import com.example.persist.mapper.DownloadHistoryWMapper;
import com.example.persist.mapper.DownloadTaskRMapper;
import com.example.persist.mapper.FileRMapper;
import com.example.util.JsonTool;
import com.google.common.base.Strings;

@Controller
public class DownloadController {

	static String FILE_SEPARATOR = "/";
	static String regEx = "[\u4e00-\u9fa5]";
	static Pattern pat = Pattern.compile(regEx);
	static final String UTF_8_CHARSET_NAME = StandardCharsets.UTF_8.name();

	@Autowired
	private DownloadTaskRMapper taskRMapper;
	@Autowired
	private FileRMapper fileRMapper;
	@Autowired
	private DownloadHistoryWMapper historyWMapper;

	private static final Logger logger = LoggerFactory
			.getLogger(DownloadController.class);

	static final String xAccelRedirect(DownloadTask task, File file)
			throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append("/protected");
		sb.append(FILE_SEPARATOR);
		sb.append(task.getProductionId());
		sb.append(FILE_SEPARATOR);
		String fileDir = file.getDir();
		if (!Strings.isNullOrEmpty(fileDir)) {
			if (fileDir.startsWith(FILE_SEPARATOR)) {
				fileDir = fileDir.substring(1);
			}
			if (fileDir.endsWith(FILE_SEPARATOR)) {
				fileDir = fileDir.substring(0, fileDir.length() - 1);
			}
			sb.append(fileDir);
			sb.append(FILE_SEPARATOR);
		}
		sb.append(URLEncoder.encode(file.getName(), UTF_8_CHARSET_NAME));
		return sb.toString();
	}

	public static boolean containChinese(String str) {
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()) {
			flg = true;
		}
		return flg;
	}

	@RequestMapping("/download/**")
	public void downloadWithAuth(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String route = request.getRequestURI();
		logger.info("route : " + route);
		logger.info("Host : " + request.getHeader("Host"));
		logger.info("X-Real-IP : " + request.getHeader("X-Real-IP"));
		logger.info("X-Forwarded-For : " + request.getHeader("X-Forwarded-For"));
		logger.info("request parameters : "
				+ JsonTool.toJson(request.getParameterMap()));
		String taskIdStr = request.getParameter("taskId");
		if (Strings.isNullOrEmpty(taskIdStr)) {
			// TODO
		}
		final long taskId = Long.parseLong(taskIdStr);
		DownloadTask task = taskRMapper.selectById(taskId);
		if (task == null) {
			// TODO
		}
		final long fileId = task.getFileId();
		File file = fileRMapper.selectById(fileId);
		if (file == null) {
			// TODO
		}
		String fileName = file.getName();
		// respond
		response.setContentType("application/octet-stream");
		String xAccelRedirect = xAccelRedirect(task, file);
		logger.info("X-Accel-Redirect: " + xAccelRedirect);
		response.setHeader("X-Accel-Redirect", xAccelRedirect);
		response.addHeader("Content-Disposition", "attachment;filename="
				+ URLEncoder.encode(fileName, UTF_8_CHARSET_NAME));
	}

	public void download(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String route = request.getRequestURI();
		logger.info("route : " + route);
		logger.info("Host : " + request.getHeader("Host"));
		logger.info("X-Real-IP : " + request.getHeader("X-Real-IP"));
		logger.info("X-Forwarded-For : " + request.getHeader("X-Forwarded-For"));
		logger.info("parameters : "
				+ JsonTool.toJson(request.getParameterMap()));
		//
		String fileRoute = route.replace("/download/", "");
		logger.debug("file route: " + fileRoute);
		String fileBaseName = fileRoute.substring(
				fileRoute.lastIndexOf("/") + 1, fileRoute.length());
		logger.debug("file base name: " + fileBaseName);
		response.setContentType("application/octet-stream");
		// 设置response的Header
		String xAccelRedirect = "/protected/" + fileRoute;
		logger.debug("X-Accel-Redirect: " + xAccelRedirect);
		response.setHeader("X-Accel-Redirect", xAccelRedirect);
		String decoded = URLDecoder.decode(fileBaseName,
				StandardCharsets.UTF_8.name());
		logger.debug("decoded: " + decoded);
		if (containChinese(decoded)) {
			logger.debug("file name contains chinese");
			response.addHeader("Content-Disposition",
					"attachment;filename*=utf-8'zh_cn'" + decoded);
		} else {
			logger.debug("file name does not contain chinese");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileBaseName);
		}
	}

	// @RequestMapping("/download/**")
	public void get(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(request.getRequestURI());
		logger.debug(request.getRequestURL().toString());
		try {
			String path = "var/gopher.jpg";
			// path是指欲下载的文件的路径。
			java.io.File file = new java.io.File(path);
			logger.debug("file length: " + file.length());
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
