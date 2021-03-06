package com.xuwuji.backend.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.db.dao.SearchDao;

@Controller
public class IndexController {
	@Autowired
	private SearchDao searchDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<String> a = new ArrayList<String>();
		a.add("a");
		return new ModelAndView("home").addObject("hotKW", a);
		// return new ModelAndView("home").addObject("hotKW",
		// searchDao.findHotKW());
	}

}
