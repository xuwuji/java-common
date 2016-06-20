package com.xuwuji.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuwuji.db.mapper.SearchMapper;
import com.xuwuji.db.model.News;
import com.xuwuji.db.util.OrderBy;
import com.xuwuji.db.util.SessionFactory;

public class SearchDao {

	public List<News> findByKeyword(String keyword, int pageNum, OrderBy order) {
		PageHelper.startPage(pageNum, 10);
		PageHelper.orderBy(order.toString() + " DESC");
		SqlSession session = SessionFactory.openDEVSession();
		List<News> list = null;
		try {
			SearchMapper mapper = session.getMapper(SearchMapper.class);
			list = mapper.findByKeyword(keyword);
			return list;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo getPageInfoByKeyword(String keyword) {
		PageHelper.startPage(1, 10);
		SqlSession session = SessionFactory.openDEVSession();
		List<News> list = null;
		try {
			SearchMapper mapper = session.getMapper(SearchMapper.class);
			list = mapper.findByKeyword(keyword);
			PageInfo page = new PageInfo(list);
			return page;
		} finally {
			session.close();
		}
	}

	public void insertRecord(String kw, String time) {
		SqlSession session = SessionFactory.openDEVSession();
		try {
			SearchMapper mapper = session.getMapper(SearchMapper.class);
			mapper.insertRecord(kw, time);
			session.commit();
		} finally {
			session.close();
		}
	}

	public ArrayList<String> findHotKW() {
		SqlSession session = SessionFactory.openDEVSession();
		ArrayList<String> list = new ArrayList<String>();
		try {
			SearchMapper mapper = session.getMapper(SearchMapper.class);
			List<HashMap<String, Object>> result = mapper.selectHot();
			for (HashMap<String, Object> map : result) {
				list.add((String) map.get("keyword"));
			}
			return list;
		} finally {
			session.close();
		}
	}

	public void insertKW(String kw, int NewsId) {
		SqlSession session = SessionFactory.openDEVSession();
		try {
			SearchMapper mapper = session.getMapper(SearchMapper.class);
			mapper.insertKW(kw, NewsId);
			session.commit();
		} finally {
			session.close();
		}
	}

}
