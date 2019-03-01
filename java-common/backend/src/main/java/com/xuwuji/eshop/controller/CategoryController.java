package com.xuwuji.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuwuji.eshop.model.Category;
import com.xuwuji.eshop.model.ParentCategory;
import com.xuwuji.eshop.model.Product;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	/**
	 * get ��ȡĳһ�������в�Ʒ
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getProductsByCategory", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getProductsByCategoryId(@RequestParam("id") String id, @RequestParam("sort") String sort,
			HttpServletRequest request, HttpServletResponse response) {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product();
		product1.setName("�����ᴽ�Ժ��405");
		product1.setMainImgUrl("http://i2.bvimg.com/677237/cc92d8022c69f686.jpg");
		product1.setPrice(1.00);
		Product product2 = new Product();
		product2.setName("dior�ϰ��Ȼ�����");
		product2.setMainImgUrl("http://i2.bvimg.com/677237/03f00d1fa6c72ddc.jpg");
		product2.setPrice(2);
		Product product3 = new Product();
		product3.setName("��ʫ���� С��ƿ�ܼ��޻��۾�����˪ ���� �������� ���۴�ȥ����");
		product3.setMainImgUrl("http://i2.bvimg.com/677237/4a2151eaf802b7f6.jpg");
		product3.setPrice(3);
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product1);
		products.add(product2);
		products.add(product3);
		if (sort == null || sort.equals("0")) {
			// Ĭ���ۺ�����

		} else if (sort.equals("1")) {
			// �����������ʱ��������ǰ

		} else if (sort.equals("2")) {
			// �۸����򣬴ӵ͵���

		} else if (sort.equals("3")) {
			// �������򣬴Ӹߵ���
		}
		return products;
	}

	/**
	 * get ��ȡĳһ�������в�Ʒ
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
		Product product1 = new Product();
		product1.setName("�����ᴽ�Ժ��405");
		product1.setMainImgUrl("http://i2.bvimg.com/677237/cc92d8022c69f686.jpg");
		product1.setPrice(1.00);
		Product product2 = new Product();
		product2.setName("dior�ϰ��Ȼ�����");
		product2.setMainImgUrl("http://i2.bvimg.com/677237/03f00d1fa6c72ddc.jpg");
		product2.setPrice(2);
		Product product3 = new Product();
		product3.setName("��ʫ���� С��ƿ�ܼ��޻��۾�����˪ ���� �������� ���۴�ȥ����");
		product3.setMainImgUrl("http://i2.bvimg.com/677237/4a2151eaf802b7f6.jpg");
		product3.setPrice(3);
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product1);
		products.add(product2);
		products.add(product3);
		products.add(product1);
		products.add(product2);
		products.add(product3);
		return products;
	}

	/**
	 * ĳһ�����µ���������
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCategoryByParent/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getCategoryByParent(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("getCategoryByParent");
		List<Category> list = new ArrayList<Category>();
		Category p1 = new Category();
		p1.setId(1);
		p1.setName("�ں����������");
		p1.setImgUrl("http://i2.bvimg.com/677237/cc92d8022c69f686.jpg");
		Category p2 = new Category();
		p2.setId(2);
		p2.setName("����");
		p2.setImgUrl("http://i2.bvimg.com/677237/0bd325eec9cfc236.jpg");
		Category p3 = new Category();
		p3.setId(3);
		p3.setName("��Ӱ");
		p3.setImgUrl("http://i2.bvimg.com/677237/bbfc8004ae1002ec.jpg");
		Category p4 = new Category();
		p4.setId(4);
		p4.setName("��ë��");
		p4.setImgUrl("http://i2.bvimg.com/677237/397b36648587defc.jpg");
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);

		if (id.equals("2")) {
			list = new ArrayList<Category>();
			list.add(p1);
		} else if (id.equals("3")) {
			list = new ArrayList<Category>();
			list.add(p2);
		} else if (id.equals("4")) {
			list = new ArrayList<Category>();
			list.add(p3);
		}

		return list;
	}

	@RequestMapping(value = "/parentCategory", method = RequestMethod.GET)
	@ResponseBody
	public List<ParentCategory> getParentCategories(HttpServletRequest request, HttpServletResponse response) {
		List<ParentCategory> list = new ArrayList<ParentCategory>();
		ParentCategory p1 = new ParentCategory();
		p1.setId(1);
		p1.setName("��ױ");
		ParentCategory p2 = new ParentCategory();
		p2.setId(2);
		p2.setName("����");
		ParentCategory p3 = new ParentCategory();
		p3.setId(3);
		p3.setName("���˻���");
		ParentCategory p4 = new ParentCategory();
		p4.setId(4);
		p4.setName("ĸӤ�׶�");
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		return list;
	}

}
