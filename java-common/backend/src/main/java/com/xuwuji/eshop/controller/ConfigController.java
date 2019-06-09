package com.xuwuji.eshop.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.eshop.model.Config;
import com.xuwuji.eshop.model.LotteryConstants;
import com.xuwuji.eshop.model.TopUpConfig;
import com.xuwuji.eshop.util.EshopConfigUtil;
import com.xuwuji.eshop.util.TokenUtil;

@Controller
@RequestMapping(value = "/config")
public class ConfigController {
	@Autowired
	private EshopConfigUtil eshopConfigUtil;

	@Autowired
	private TopUpConfig topupConfig;

	@RequestMapping(value = "/getBaseConfig", method = RequestMethod.GET)
	@ResponseBody
	public EshopConfigResponse geBaseConfig(HttpServletRequest request, HttpServletResponse response) {
		EshopConfigResponse eshopConfig = new EshopConfigResponse();
		eshopConfig.setNoticeMsg(eshopConfigUtil.getParam(eshopConfigUtil.noticeMsg));
		eshopConfig.setXxcUrl(eshopConfigUtil.getParam(eshopConfigUtil.xxcUrl));
		eshopConfig.setShareTitle(eshopConfigUtil.getParam(eshopConfigUtil.SHARE_TITLE));
		eshopConfig.setGoldDiscount(eshopConfigUtil.getParam(eshopConfigUtil.GOLD_DISCOUNT));
		eshopConfig.setPlatinumDiscount(eshopConfigUtil.getParam(eshopConfigUtil.PLATINUM_DISCOUNT));
		eshopConfig.setDiamondDiscount(eshopConfigUtil.getParam(eshopConfigUtil.DiAMOND_DISCOUNT));
		eshopConfig.setShowNoticeMessage(eshopConfigUtil.getParam(eshopConfigUtil.SHOW_NOTICE_MESSAGE));
		eshopConfig.setLicenceImgUrl(eshopConfigUtil.getParam(eshopConfigUtil.LICENCE_IMG_URL));
		eshopConfig.setShareImgUrl(eshopConfigUtil.getParam(eshopConfigUtil.SHARE_IMG_URL));
		eshopConfig.setBonusImgUrl(eshopConfigUtil.getParam(eshopConfigUtil.BONUS_IMG_URL));
		eshopConfig.setMinMoney(eshopConfigUtil.getParam(eshopConfigUtil.MIN_MONEY));
		eshopConfig.setLotteryStageOne(eshopConfigUtil.getParam(LotteryConstants.LOTTERY_STAGE_ONE));
		eshopConfig.setLotteryStageTwo(eshopConfigUtil.getParam(LotteryConstants.LOTTERY_STAGE_TWO));
		eshopConfig.setAmountPerInStageOne(eshopConfigUtil.getParam(LotteryConstants.AMOUNT_PER_IN_STAGE_ONE));
		eshopConfig.setAmountMaxInStageOne(eshopConfigUtil.getParam(LotteryConstants.AMOUNT_MAX_IN_STAGE_ONE));
		eshopConfig.setAmountPerInStageTwo(eshopConfigUtil.getParam(LotteryConstants.AMOUNT_PER_IN_STAGE_TWO));
		eshopConfig.setAmountMaxInStageTwo(eshopConfigUtil.getParam(LotteryConstants.AMOUNT_MAX_IN_STAGE_TWO));
		eshopConfig.setAmountPerOverStageTwo(eshopConfigUtil.getParam(LotteryConstants.AMOUNR_PER_OVER_STAGE_TWO));
		eshopConfig.setLotteryShareTitle(eshopConfigUtil.getParam(eshopConfigUtil.LOTTERY_SHARE_TITLE));
		return eshopConfig;
	}

	@RequestMapping(value = "/getOpenId", method = RequestMethod.GET)
	@ResponseBody
	public EshopConfigResponse getOpenId(HttpServletRequest request, HttpServletResponse response) {
		EshopConfigResponse eshopConfig = new EshopConfigResponse();
		String code = request.getParameter("code");
		eshopConfig.setOpenId(TokenUtil.getOpenId(code));
		return eshopConfig;
	}

	@RequestMapping(value = "/getShareImg", method = RequestMethod.GET)
	@ResponseBody
	public EshopConfigResponse getShareGroupImg(HttpServletRequest request, HttpServletResponse response) {
		EshopConfigResponse eshopConfig = new EshopConfigResponse();
		eshopConfig.setShareGroupImg(eshopConfigUtil.getParam(eshopConfigUtil.SHARE_GROUP_IMG));
		eshopConfig.setWechatCode(eshopConfigUtil.getParam(eshopConfigUtil.WECHAT_CODE));
		return eshopConfig;
	}

	@RequestMapping(value = "/topupConfig", method = RequestMethod.GET)
	@ResponseBody
	public TopUpConfig topupConfig(HttpServletRequest request, HttpServletResponse response) {
		return topupConfig;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/eshop/config");
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Config> getAll(HttpServletRequest request, HttpServletResponse response) {
		return eshopConfigUtil.getAll();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public void add(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		String description = request.getParameter("description");
		Config config = new Config();
		config.setName(name);
		config.setDescription(description);
		config.setValue(value);
		eshopConfigUtil.addParam(config);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		String description = request.getParameter("description");
		String id = request.getParameter("id");
		Config config = new Config();
		config.setName(name);
		config.setDescription(description);
		config.setValue(value);
		config.setId(Integer.valueOf(id));
		eshopConfigUtil.updateParam(config);
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getPreSearch", method = RequestMethod.GET)
	@ResponseBody
	public EshopConfigResponse getPreSearch(HttpServletRequest request, HttpServletResponse response) {
		EshopConfigResponse eshopConfig = new EshopConfigResponse();
		eshopConfig.setPreSearch(Arrays.asList(eshopConfigUtil.getParam(eshopConfigUtil.PRE_SEARCH).split("/")));
		return eshopConfig;
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getArticlePreSearch", method = RequestMethod.GET)
	@ResponseBody
	public EshopConfigResponse getArticlePreSearch(HttpServletRequest request, HttpServletResponse response) {
		EshopConfigResponse eshopConfig = new EshopConfigResponse();
		eshopConfig.setArticlePreSearch(
				Arrays.asList(eshopConfigUtil.getParam(eshopConfigUtil.ARTICLE_PRE_SEARCH).split("/")));
		return eshopConfig;
	}

	class EshopConfigResponse {
		public String xxcUrl;
		public String noticeMsg;
		public String openId;
		public List<String> preSearch;
		public List<String> articlePreSearch;
		public String shareTitle;
		public String goldDiscount;
		public String platinumDiscount;
		public String diamondDiscount;
		public String showNoticeMessage;
		public String licenceImgUrl;
		public String shareImgUrl;
		public String articleImgUrl;
		public String bonusImgUrl;
		public String minMoney;
		// 第一阶段
		public String lotteryStageOne;
		// 第二阶段
		public String lotteryStageTwo;
		// 第一阶段内单次最多抽到的金额
		public String amountPerInStageOne;
		// 第一阶段最多抽到的金额
		public String amountMaxInStageOne;
		// 第二阶段内单次最多抽到的金额
		public String amountPerInStageTwo;
		// 第二阶段最多抽到的金额
		public String amountMaxInStageTwo;
		// 第二阶段外单次最多抽到的金额
		public String amountPerOverStageTwo;
		public String lotteryShareTitle;
		public String shareGroupImg;
		public String wechatCode;

		public String getXxcUrl() {
			return xxcUrl;
		}

		public void setXxcUrl(String xxcUrl) {
			this.xxcUrl = xxcUrl;
		}

		public String getNoticeMsg() {
			return noticeMsg;
		}

		public void setNoticeMsg(String noticeMsg) {
			this.noticeMsg = noticeMsg;
		}

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

		public List<String> getPreSearch() {
			return preSearch;
		}

		public void setPreSearch(List<String> preSearch) {
			this.preSearch = preSearch;
		}

		public List<String> getArticlePreSearch() {
			return articlePreSearch;
		}

		public void setArticlePreSearch(List<String> articlePreSearch) {
			this.articlePreSearch = articlePreSearch;
		}

		public String getShareTitle() {
			return shareTitle;
		}

		public void setShareTitle(String shareTitle) {
			this.shareTitle = shareTitle;
		}

		public String getGoldDiscount() {
			return goldDiscount;
		}

		public void setGoldDiscount(String goldDiscount) {
			this.goldDiscount = goldDiscount;
		}

		public String getPlatinumDiscount() {
			return platinumDiscount;
		}

		public void setPlatinumDiscount(String platinumDiscount) {
			this.platinumDiscount = platinumDiscount;
		}

		public String getDiamondDiscount() {
			return diamondDiscount;
		}

		public void setDiamondDiscount(String diamondDiscount) {
			this.diamondDiscount = diamondDiscount;
		}

		public String getShowNoticeMessage() {
			return showNoticeMessage;
		}

		public void setShowNoticeMessage(String showNoticeMessage) {
			this.showNoticeMessage = showNoticeMessage;
		}

		public String getLicenceImgUrl() {
			return licenceImgUrl;
		}

		public void setLicenceImgUrl(String licenceImgUrl) {
			this.licenceImgUrl = licenceImgUrl;
		}

		public String getShareImgUrl() {
			return shareImgUrl;
		}

		public void setShareImgUrl(String shareImgUrl) {
			this.shareImgUrl = shareImgUrl;
		}

		public String getArticleImgUrl() {
			return articleImgUrl;
		}

		public void setArticleImgUrl(String articleImgUrl) {
			this.articleImgUrl = articleImgUrl;
		}

		public String getBonusImgUrl() {
			return bonusImgUrl;
		}

		public void setBonusImgUrl(String bonusImgUrl) {
			this.bonusImgUrl = bonusImgUrl;
		}

		public String getMinMoney() {
			return minMoney;
		}

		public void setMinMoney(String minMoney) {
			this.minMoney = minMoney;
		}

		public String getLotteryStageOne() {
			return lotteryStageOne;
		}

		public void setLotteryStageOne(String lotteryStageOne) {
			this.lotteryStageOne = lotteryStageOne;
		}

		public String getLotteryStageTwo() {
			return lotteryStageTwo;
		}

		public void setLotteryStageTwo(String lotteryStageTwo) {
			this.lotteryStageTwo = lotteryStageTwo;
		}

		public String getAmountPerInStageOne() {
			return amountPerInStageOne;
		}

		public void setAmountPerInStageOne(String amountPerInStageOne) {
			this.amountPerInStageOne = amountPerInStageOne;
		}

		public String getAmountMaxInStageOne() {
			return amountMaxInStageOne;
		}

		public void setAmountMaxInStageOne(String amountMaxInStageOne) {
			this.amountMaxInStageOne = amountMaxInStageOne;
		}

		public String getAmountPerInStageTwo() {
			return amountPerInStageTwo;
		}

		public void setAmountPerInStageTwo(String amountPerInStageTwo) {
			this.amountPerInStageTwo = amountPerInStageTwo;
		}

		public String getAmountMaxInStageTwo() {
			return amountMaxInStageTwo;
		}

		public void setAmountMaxInStageTwo(String amountMaxInStageTwo) {
			this.amountMaxInStageTwo = amountMaxInStageTwo;
		}

		public String getAmountPerOverStageTwo() {
			return amountPerOverStageTwo;
		}

		public void setAmountPerOverStageTwo(String amountPerOverStageTwo) {
			this.amountPerOverStageTwo = amountPerOverStageTwo;
		}

		public String getLotteryShareTitle() {
			return lotteryShareTitle;
		}

		public void setLotteryShareTitle(String lotteryShareTitle) {
			this.lotteryShareTitle = lotteryShareTitle;
		}

		public String getShareGroupImg() {
			return shareGroupImg;
		}

		public void setShareGroupImg(String shareGroupImg) {
			this.shareGroupImg = shareGroupImg;
		}

		public String getWechatCode() {
			return wechatCode;
		}

		public void setWechatCode(String wechatCode) {
			this.wechatCode = wechatCode;
		}

	}

}
