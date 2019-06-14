package com.xuwuji.eshop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.eshop.db.dao.ArticleDao;
import com.xuwuji.eshop.db.dao.CategoryDao;
import com.xuwuji.eshop.db.dao.ProductDao;
import com.xuwuji.eshop.db.dao.SearchHistoryDao;
import com.xuwuji.eshop.db.dao.UserDao;
import com.xuwuji.eshop.db.dao.ViewHistoryDao;
import com.xuwuji.eshop.model.Article;
import com.xuwuji.eshop.model.Category;
import com.xuwuji.eshop.model.Product;
import com.xuwuji.eshop.model.SearchHistory;
import com.xuwuji.eshop.model.SortEnum;
import com.xuwuji.eshop.model.User;
import com.xuwuji.eshop.model.UserState;
import com.xuwuji.eshop.model.ViewHistory;
import com.xuwuji.eshop.util.EshopConfigUtil;
import com.xuwuji.eshop.util.HttpUtil;
import com.xuwuji.eshop.util.ProductUtil;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private EshopConfigUtil eshopConfigUtil;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ViewHistoryDao viewHistoryDao;
	@Autowired
	private SearchHistoryDao searchHistoryDao;
	@Autowired
	private ProductUtil productUtil;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/eshop/article");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public void add(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String referProductId = request.getParameter("referProductId");
		String referCategoryId = request.getParameter("referCategoryId");
		String text = request.getParameter("text");
		String tags = request.getParameter("tags");
		// String imgs = request.getParameter("imgs");
		String typeId = request.getParameter("typeId");
		// String mainImgUrl = request.getParameter("mainImgUrl");
		String announceStyle = request.getParameter("announceStyle");
		Article article = new Article();
		article.setTitle(title);
		article.setReferProductId(referProductId);
		article.setReferCategoryId(referCategoryId);
		// article.setImgs(imgs);
		article.setTags(tags);
		article.setText(text);
		article.setTypeId(typeId);
		// article.setMainImgUrl(mainImgUrl);
		article.setAnnounceStyle(announceStyle);
		articleDao.add(article);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String referProductId = request.getParameter("referProductId");
		String referCategoryId = request.getParameter("referCategoryId");
		String text = request.getParameter("text");
		String tags = request.getParameter("tags");
		String imgs = request.getParameter("imgs");
		String id = request.getParameter("id");
		String typeId = request.getParameter("typeId");
		String mainImgUrl = request.getParameter("mainImgUrl");
		String announceStyle = request.getParameter("announceStyle");
		Article article = new Article();
		article.setTitle(title);
		article.setReferProductId(referProductId);
		article.setReferCategoryId(referCategoryId);
		article.setImgs(imgs);
		article.setTags(tags);
		article.setText(text);
		article.setMainImgUrl(mainImgUrl);
		article.setId(Integer.valueOf(id));
		article.setTypeId(typeId);
		article.setAnnounceStyle(announceStyle);
		articleDao.update(article);
	}

	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public Article getById(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		List<Article> list = new ArrayList<Article>();
		list = articleDao.getById(id);
		String articleImgBase = eshopConfigUtil.getParam(eshopConfigUtil.ARTICLE_IMG_BASE);
		if (list.size() == 1) {
			Article article = list.get(0);
			article.setMainImgUrl(articleImgBase + id + "-0.jpg");
			List<String> imgUrls = new ArrayList<String>();
			for (int i = 1; i < 5; i++) {
				String swipeImgUrl = articleImgBase + id + "-" + i + ".jpg";
				if (HttpUtil.checkValid(swipeImgUrl)) {
					imgUrls.add(swipeImgUrl);
				}
			}
			article.setImgUrlList(imgUrls);
			article.setTagList(Arrays.asList(article.getTags().split(";")));
			return article;
		}
		return null;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Article> getAll(HttpServletRequest request, HttpServletResponse response) {
		List<Article> list = new ArrayList<Article>();
		list = articleDao.getAll();
		return list;
	}

	@RequestMapping(value = "/getActiveAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Article> getActiveAll(HttpServletRequest request, HttpServletResponse response) {
		List<Article> list = new ArrayList<Article>();
		list = articleDao.getActiveAll();
		return list;
	}

	@RequestMapping(value = "/getActiveAllForHomePage", method = RequestMethod.GET)
	@ResponseBody
	public List<Article> getActiveAllForHomePage(HttpServletRequest request, HttpServletResponse response) {
		List<Article> list = new ArrayList<Article>();
		List<Article> result = new ArrayList<Article>();
		list = articleDao.getActiveAllByCondition("", "1", "");
		// 打乱顺序，随机展示
		Collections.shuffle(list);
		// 最多展示10个
		if (list.size() > 10) {
			list = list.subList(0, 10);
		}
		// 只返回简短信息
		String ARTICLE_IMG_BASE = eshopConfigUtil.getParam(eshopConfigUtil.ARTICLE_IMG_BASE);
		String PRODUCT_IMG_BASE = eshopConfigUtil.getParam(eshopConfigUtil.PRODUCT_IMG_BASE);
		for (Article article : list) {
			Article temp = new Article();
			temp.setId(article.getId());
			temp.setReferProductId(article.getReferProductId());
			temp.setHomeImgUrl(ARTICLE_IMG_BASE + "home-" + article.getId() + ".jpg");
			Product product = productDao.getById(article.getReferProductId());
			product.setMainImgUrl(PRODUCT_IMG_BASE + product.getId() + "-0.jpg");
			temp.setProduct(product);
			result.add(temp);
		}
		return result;
	}

	@RequestMapping(value = "/getActiveAllByCondition", method = RequestMethod.GET)
	@ResponseBody
	public List<Article> getActiveAllByCondition(HttpServletRequest request, HttpServletResponse response) {
		String tag = request.getParameter("tag");
		String typeId = request.getParameter("typeId");
		String title = request.getParameter("title");
		List<Article> list = new ArrayList<Article>();
		HashSet<Integer> idSet = new HashSet<Integer>();
		List<Article> temp = articleDao.getActiveAllByCondition(tag, typeId, title);
		String articleImgBase = eshopConfigUtil.getParam(eshopConfigUtil.ARTICLE_IMG_BASE);
		for (Article article : temp) {
			if (!idSet.contains(article.getId())) {
				article.setMainImgUrl(articleImgBase + article.getId() + "-0.jpg");
				// article.setImgUrlList(Arrays.asList(article.getImgs().split(";")));
				article.setTagList(Arrays.asList(article.getTags().split(";")));
				list.add(article);
				idSet.add(article.getId());
			}
		}
		return list;
	}

	@RequestMapping(value = "/getActiveAllByTags", method = RequestMethod.GET)
	@ResponseBody
	public List<Article> getActiveAllByTags(@RequestParam("tags") String tags, HttpServletRequest request,
			HttpServletResponse response) {
		List<Article> list = new ArrayList<Article>();
		HashSet<Integer> idSet = new HashSet<Integer>();
		String articleImgBase = eshopConfigUtil.getParam(eshopConfigUtil.ARTICLE_IMG_BASE);
		for (String tag : tags.split(";")) {
			List<Article> temp = articleDao.getActiveAllByTags(tag);
			for (Article article : temp) {
				if (!idSet.contains(article.getId())) {
					// article.setImgUrlList(Arrays.asList(article.getImgs().split(";")));
					article.setTagList(Arrays.asList(article.getTags().split(";")));
					article.setMainImgUrl(articleImgBase + article.getId() + "-0.jpg");
					list.add(article);
					idSet.add(article.getId());
				}
			}
		}
		return list;
	}

	@RequestMapping(value = "/getGuess", method = RequestMethod.GET)
	@ResponseBody
	public List<Article> getGuess(HttpServletRequest request, HttpServletResponse response) {
		List<Article> list = new ArrayList<Article>();
		HashSet<Integer> idSet = new HashSet<Integer>();
		String openId = request.getParameter("openId");
		HashSet<String> categoryIds = new HashSet<String>();
		HashSet<String> productIds = new HashSet<String>();
		// 系统自定义推荐的类别
		List<Category> categories = new ArrayList<Category>();
		categories = categoryDao.getRecommend();
		for (Category c : categories) {
			int categoryId = c.getId();
			categoryIds.add(String.valueOf(categoryId));
		}
		// 浏览历史
		List<ViewHistory> viewHistoryList = viewHistoryDao.getAllByOpenId(openId);
		for (ViewHistory viewHistory : viewHistoryList) {
			String categoryId = viewHistory.getCategoryId();
			String productId = viewHistory.getProductId();
			categoryIds.add(categoryId);
			productIds.add(productId);
		}
		// 搜索历史
		List<SearchHistory> searchHistoryList = searchHistoryDao.getAllByOpenId(openId);
		for (SearchHistory searchHistory : searchHistoryList) {
			String kw = searchHistory.getKw();
			List<Product> products = new ArrayList<Product>();
			products = productDao.getActiveByKW(kw);
			for (Product product : products) {
				productIds.add(String.valueOf(product.getId()));
			}
		}
		// 根据用户以前浏览和搜索到的商品和类别添加所相关的
		List<Article> referArticles = new ArrayList<Article>();
		referArticles = articleDao.getByRefer(productIds, categoryIds);
		for (Article article : referArticles) {
			if (!idSet.contains(article.getId())) {
				list.add(article);
				idSet.add(article.getId());
			}
		}
		// 对于预先设置好的搜索词进行查询添加
		List<String> preSearchArr = Arrays
				.asList(eshopConfigUtil.getParam(eshopConfigUtil.ARTICLE_PRE_SEARCH).split("/"));
		for (String word : preSearchArr) {
			List<Article> tagTemp = articleDao.getActiveAllByCondition(word, "", "");
			List<Article> titleTemp = articleDao.getActiveAllByCondition("", "", word);
			for (Article article : tagTemp) {
				if (!idSet.contains(article.getId())) {
					list.add(article);
					idSet.add(article.getId());
				}
			}
			for (Article article : titleTemp) {
				if (!idSet.contains(article.getId())) {
					list.add(article);
					idSet.add(article.getId());
				}
			}
		}

		String articleImgBase = eshopConfigUtil.getParam(eshopConfigUtil.ARTICLE_IMG_BASE);
		for (Article article : list) {
			article.setMainImgUrl(articleImgBase + article.getId() + "-0.jpg");
		}
		return list;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public List<Article> search(HttpServletRequest request, HttpServletResponse response) {
		String kw = request.getParameter("kw");
		List<Article> list = new ArrayList<Article>();
		HashSet<Integer> idSet = new HashSet<Integer>();
		List<Article> tagTemp = articleDao.getActiveAllByCondition(kw, "", "");
		List<Article> titleTemp = articleDao.getActiveAllByCondition("", "", kw);
		String articleImgBase = eshopConfigUtil.getParam(eshopConfigUtil.ARTICLE_IMG_BASE);
		for (Article article : tagTemp) {
			if (!idSet.contains(article.getId())) {
				list.add(article);
				idSet.add(article.getId());
			}
		}
		for (Article article : titleTemp) {
			if (!idSet.contains(article.getId())) {
				list.add(article);
				idSet.add(article.getId());
			}
		}
		for (Article article : list) {
			article.setMainImgUrl(articleImgBase + article.getId() + "-0.jpg");
		}
		return list;
	}

	@RequestMapping(value = "/disable", method = RequestMethod.GET)
	@ResponseBody
	public void disable(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		articleDao.disable(id);
	}

	@RequestMapping(value = "/reActive", method = RequestMethod.GET)
	@ResponseBody
	public void reActive(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		articleDao.reActive(id);
	}

}
