package com.xuwuji.eshop.controller;

import java.io.IOException;
import java.io.OutputStream;
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

import com.xuwuji.eshop.db.dao.ProductDao;
import com.xuwuji.eshop.model.Product;
import com.xuwuji.eshop.model.SortEnum;
import com.xuwuji.eshop.util.EshopConfigUtil;
import com.xuwuji.eshop.util.ProductUtil;
import com.xuwuji.eshop.util.QRCodeUtil;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductUtil productUtil;

	@Autowired
	private QRCodeUtil qRCodeUtil;

	@Autowired
	private EshopConfigUtil eshopConfigUtil;

	/**
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getQRCode/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void getQRCode(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		@SuppressWarnings("static-access")
		byte[] result = qRCodeUtil.getQRCoderByte(id);
		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		os.write(result);
		os.flush();
		os.close();
	}

	/**
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getActiveProductsByCategoryId", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getProductsByCategoryId(@RequestParam("id") String id, @RequestParam("sort") String sort,
			HttpServletRequest request, HttpServletResponse response) {
		List<Product> products = new ArrayList<Product>();
		SortEnum sortRequset = SortEnum.getByCode(sort);
		products = productDao.getActiveByCategory(id);
		products = productUtil.sort(products, sortRequset);
		return products;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Product getProductById(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(id);
		if (id == null || id.equals("undefined")) {
			id = "1";
		}
		Product product = productDao.getById(id);
		List<String> imgUrls = new ArrayList<String>();
		for (int i = 1; i < 5; i++) {
			imgUrls.add(eshopConfigUtil.PRODUCT_IMG_BASE + id + "-" + i + ".jpg");
		}
		product.setImgUrls(imgUrls);
		String mainImgUrl = eshopConfigUtil.PRODUCT_IMG_BASE + id + "-0.jpg";
		product.setMainImgUrl(mainImgUrl);
		return product;
	}

	/**
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getActiveByBannerItem", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getActiveByBannerItem(@RequestParam("id") String id, @RequestParam("sort") String sort,
			HttpServletRequest request, HttpServletResponse response) {
		List<Product> products = new ArrayList<Product>();
		SortEnum sortRequset = SortEnum.getByCode(sort);
		products = productDao.getActiveByBannerItem(id);
		products = productUtil.sort(products, sortRequset);
		return products;
	}
}
