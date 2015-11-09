package com.example.webgui.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.Production;
import com.example.persist.rdbms.ProductionRMapper;
import com.example.persist.rdbms.ProductionWMapper;
import com.example.webapi.RouteDefine;

@Controller
public class AdminWebGuiProductionController {

	static final String DELETE = "admin/prod_delete";
	static final String LIST = "admin/prod_list";
	static final String NEW = "admin/prod_new";

	private static final Logger logger = LoggerFactory
			.getLogger(AdminWebGuiProductionController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private ProductionRMapper rMapper;
	@Autowired
	private ProductionWMapper wMapper;

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew() {
		return ModelAndViewTool.newModelAndView(appConfig, NEW);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS, method = RequestMethod.POST)
	public ModelAndView newProduction(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		logger.debug("name: " + name);
		String description = request.getParameter("description");
		logger.debug("description: " + description);
		Production e = new Production();
		e.reset();
		e.setName(name);
		e.setDescription(description);
		wMapper.insert(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_DELETE, method = RequestMethod.GET)
	public ModelAndView gotoDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter("id");
		final long id = Long.parseLong(idStr);
		Production e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, DELETE);
		Map<String, Object> model = ret.getModel();
		model.put("id", e.getId());
		model.put("name", e.getName());
		model.put("description", e.getDescription());
		return ret;
	}
	
	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_DELETE, method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter("id");
		final long id = Long.parseLong(idStr);
		Production e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		e.disable();
		wMapper.updateById(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

}
