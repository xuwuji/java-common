package com.xuwuji.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuwuji.eshop.db.dao.CategoryDao;
import com.xuwuji.eshop.db.dao.ParentCategoryDao;
import com.xuwuji.eshop.db.dao.ProductDao;
import com.xuwuji.eshop.model.Category;
import com.xuwuji.eshop.model.ParentCategory;
import com.xuwuji.eshop.model.Product;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ParentCategoryDao parentCategoryDao;

	
	

	/**
	 * get 获取某一类别的所有产品
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getProductsByCategory/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getProductsByCategoryId(@PathVariable("id") String id, HttpServletResponse response) {
		List<Product> products = new ArrayList<Product>();
		products = productDao.getByCategory(id);
		return products;
	}

	/**
	 * 某一大类下的所有子类
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCategoryByParent/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getCategoryByParent(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		List<Category> list = new ArrayList<Category>();
		list = categoryDao.getByParent(id);
		return list;
	}

	/**
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getActiveCategoryByParent/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getActiveCategoryByParent(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		List<Category> list = new ArrayList<Category>();
		list = categoryDao.getActiveByParent(id);
		return list;
	}

	@RequestMapping(value = "/parentCategory", method = RequestMethod.GET)
	@ResponseBody
	public List<ParentCategory> getParentCategories(HttpServletRequest request, HttpServletResponse response) {
		List<ParentCategory> list = new ArrayList<ParentCategory>();
		list = parentCategoryDao.getAll();
		return list;
	}

	@RequestMapping(value = "/activeParentCategory", method = RequestMethod.GET)
	@ResponseBody
	public List<ParentCategory> getAciveParentCategories(HttpServletRequest request, HttpServletResponse response) {
		List<ParentCategory> list = new ArrayList<ParentCategory>();
		list = parentCategoryDao.getActiveAll();
		return list;
	}

}
