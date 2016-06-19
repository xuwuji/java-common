package com.xuwuji.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuwuji.backend.cache.HistoryCacheUtil;
import com.xuwuji.db.dao.NewsDao;
import com.xuwuji.db.model.News;
import com.xuwuji.db.model.User;

@Controller
@RequestMapping(value = "/history")
public class HistoryController {
	@Autowired
	private HistoryCacheUtil historyCacheUtil;
	@Autowired
	private NewsDao newsDao;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<String[]> getHistory(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getAttribute("user");
		System.out.println(user);
		List<String[]> result = null;
		if (user != null) {
			result = new ArrayList<String[]>();
			List<String> records = historyCacheUtil.getAllWatchedHistory(user.getUsername());
			System.out.println(records);
			for (String record : records) {
				String[] strs = record.split("@");
				String id = strs[0];
				String time = strs[1];
				News n = newsDao.findInfoById(Integer.valueOf(id));
				String[] info = new String[3];
				info[0] = n.getTitle();
				info[1] = n.getType();
				info[2] = time;
				result.add(info);
			}
		}
		return result;
	}

}
