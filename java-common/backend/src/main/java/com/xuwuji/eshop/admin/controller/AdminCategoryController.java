package com.xuwuji.eshop.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.netty.util.internal.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.eshop.model.Category;
import com.xuwuji.eshop.model.ParentCategory;

/**
 * category�ӷ������
 * 
 * @author xuwuji
 *
 */
@Controller
@RequestMapping(value = "/admin/category")
public class AdminCategoryController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/eshop/category");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public void add(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		String parentCategoryId = request.getParameter("parentCategoryId");
		String imgUrl = request.getParameter("imgUrl");
		System.out.println(name);
		System.out.println(desc);
		System.out.println(parentCategoryId);
		System.out.println(imgUrl);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		// ����ɾ��
		if (type.equals("single")) {

		}
		// ����ɾ��
		else if (type.equals("batch")) {
			List<String> ids = Arrays.asList(id.split(","));
			System.out.println(ids);
		}
		System.out.println(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		System.out.println(id);
		System.out.println(name);

	}

}
