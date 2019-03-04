package com.xuwuji.eshop.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.xuwuji.eshop.db.mapper.ProductMapper;
import com.xuwuji.eshop.db.util.SessionFactory;
import com.xuwuji.eshop.model.Product;

@Component
public class ProductDao {

	public void add(Product product) {
		SqlSession session = SessionFactory.openDEVSession();
		try {
			ProductMapper mapper = session.getMapper(ProductMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", product.getName());
			map.put("description", product.getDescription());
			map.put("price", product.getPrice());
			map.put("inventory", product.getInventory());
			map.put("salesCount", product.getSalesCount());
			map.put("mainImgUrl", product.getMainImgUrl());
			map.put("parentCategoryId", product.getParentCategoryId());
			map.put("categoryId", product.getCategoryId());
			map.put("brandNameCN", product.getBrandNameCN());
			map.put("brandNameEN", product.getBrandNameEN());
			mapper.add(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	public void disable(String id) {
		SqlSession session = SessionFactory.openDEVSession();
		try {
			ProductMapper mapper = session.getMapper(ProductMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			mapper.disable(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	};

	public void reActive(String id) {
		SqlSession session = SessionFactory.openDEVSession();
		try {
			ProductMapper mapper = session.getMapper(ProductMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			mapper.reActive(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	};

	public void update(Product product) {
		SqlSession session = SessionFactory.openDEVSession();
		try {
			ProductMapper mapper = session.getMapper(ProductMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", product.getId());
			map.put("name", product.getName());
			map.put("description", product.getDescription());
			map.put("price", product.getPrice());
			map.put("inventory", product.getInventory());
			map.put("salesCount", product.getSalesCount());
			map.put("mainImgUrl", product.getMainImgUrl());
			map.put("parentCategoryId", product.getParentCategoryId());
			map.put("categoryId", product.getCategoryId());
			map.put("brandNameCN", product.getBrandNameCN());
			map.put("brandNameEN", product.getBrandNameEN());
			mapper.update(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	public List<Product> getByCategory(String categoryId) {
		SqlSession session = SessionFactory.openDEVSession();
		List<Product> result = new ArrayList<Product>();
		try {
			ProductMapper mapper = session.getMapper(ProductMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("categoryId", categoryId);
			result = mapper.getByCategory(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	public List<Product> getActiveByCategory(String categoryId) {
		SqlSession session = SessionFactory.openDEVSession();
		List<Product> result = new ArrayList<Product>();
		try {
			ProductMapper mapper = session.getMapper(ProductMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("categoryId", categoryId);
			result = mapper.getActiveByCategory(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	public Product getById(String productId) {
		SqlSession session = SessionFactory.openDEVSession();
		Product result = new Product();
		try {
			ProductMapper mapper = session.getMapper(ProductMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", productId);
			result = mapper.getById(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

}
