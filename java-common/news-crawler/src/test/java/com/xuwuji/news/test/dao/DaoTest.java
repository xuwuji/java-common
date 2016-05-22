package com.xuwuji.news.test.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xuwuji.news.dao.NewsDao;
import com.xuwuji.news.model.News;

public class DaoTest {

	// @Test
	public void testInsert() {
		NewsDao dao = new NewsDao();
		News news = new News();
		news.setTitle("for testing use");
		List<News> list = dao.findByKeyword("for testing use");
		int i = 0;
		for (News n : list) {
			if (n.getTitle().equals("for testing use")) {
				i++;
				break;
			}
		}
		assertEquals(1, i);
	}

	// @Test
	public void getTypes() {
		NewsDao dao = new NewsDao();
		System.out.println(dao.getTypes());
	}

	@Test
	public void getBigCategoryByType() {
		NewsDao dao = new NewsDao();
		for (String type : dao.getTypes()) {
			System.out.println(dao.getBigCategoryByType(type));
		}
	}

	@Test
	public void getsubCategoryByBig() {
		NewsDao dao = new NewsDao();
		for (String type : dao.getTypes()) {
			for (String bigCategory : dao.getBigCategoryByType(type)) {
				System.out.println(dao.getsubCategoryByBig(bigCategory));
			}
		}
	}

	// @Test
	public void findNewsByCategory() {
		NewsDao dao = new NewsDao();
		ArrayList<News> list = (ArrayList<News>) dao.findNewsByCategory("aa", "bb", "cc");
		System.out.println(list);
	}
}
