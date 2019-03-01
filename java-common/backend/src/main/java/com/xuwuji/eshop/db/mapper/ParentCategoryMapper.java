package com.xuwuji.eshop.db.mapper;

import java.util.HashMap;
import java.util.List;

import com.xuwuji.eshop.model.ParentCategory;

public interface ParentCategoryMapper {

	public void add(HashMap<String, Object> map);

	public void disable(HashMap<String, Object> map);

	public void reActive(HashMap<String, Object> map);
	
	public List<ParentCategory> getAll();
}
