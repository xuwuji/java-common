package com.xuwuji.db.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuwuji.db.model.News;

public interface SearchMapper {

	List<News> findByKeyword(String keyword);

	List<News> findByKeywordAndCategory(@Param("keyword") String keyword, @Param("category") String category);

	void insertRecord(@Param("keyword") String keyword, @Param("time") String time);

	List<HashMap<String, Object>> selectHot();

	void insertKW(@Param("keyword") String keyword, @Param("NewsId") Integer NewsId);
}
