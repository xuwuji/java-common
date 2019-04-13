package com.xuwuji.eshop.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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

import com.xuwuji.eshop.db.dao.OrderDao;
import com.xuwuji.eshop.db.dao.OrderItemDao;
import com.xuwuji.eshop.db.dao.UserDao;
import com.xuwuji.eshop.model.Category;
import com.xuwuji.eshop.model.Img;
import com.xuwuji.eshop.model.Order;
import com.xuwuji.eshop.model.ParentCategory;
import com.xuwuji.eshop.model.User;
import com.xuwuji.eshop.model.UserLevel;
import com.xuwuji.eshop.util.EshopConfigUtil;

/**
 * product����
 * 
 * @author xuwuji
 *
 */
@Controller
@RequestMapping(value = "/admin/order")
public class AdminOrderController {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EshopConfigUtil eshopConfigUtil;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/eshop/order");
	}

	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	@ResponseBody
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		// ����ɾ��
		if (type.equals("single")) {

		}
		// ����ɾ��
		else if (type.equals("batch")) {
			List<String> ids = Arrays.asList(id.split(","));
			System.out.println(ids);
		}
		System.out.println(id);
	}

	@RequestMapping(value = "/deliver", method = RequestMethod.POST)
	@ResponseBody
	public void deliver(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		String logisticsId = request.getParameter("logisticsId");
		String logisticsName = request.getParameter("logisticsName");
		System.out.println(orderId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		String state = request.getParameter("state");
		String logisticsId = request.getParameter("logisticsId");
		String logisticsName = request.getParameter("logisticsName");
		String memo = request.getParameter("memo");
		Order order = new Order();
		order.setOrderId(orderId);
		order.setState(state);
		order.setLogisticsId(logisticsId);
		order.setLogisticsName(logisticsName);
		order.setMemo(memo);
		orderDao.update(order);
	}

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ResponseBody
	public void pay(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		String state = request.getParameter("state");
		Order updateOrder = new Order();
		updateOrder.setState(state);
		updateOrder.setOrderId(orderId);
		orderDao.update(updateOrder);
		// user related
		Order order = orderDao.getOrderInfoByOrderId(orderId);
		String openId = order.getOpenId();
		String source = order.getSource();
		User buyer = new User();
		buyer.setOpenId(openId);
		User userFromDB = userDao.getByCondition(buyer);
		// 表里没储存说明是新用户，需要添加到表内
		if (userFromDB.getId() == 0 || userFromDB.getLevel().equals("0")) {
			userDao.add(buyer);
		}
		//如果此订单是通过分享进行下单的，则对分享人进行红包奖励
		if (source.equals("share")) {
			String sourceOpenId = order.getSourceOpenId();
			User resourcer = new User();
			resourcer.setOpenId(sourceOpenId);
			resourcer = userDao.getByCondition(resourcer);
			resourcer.setBonusAmount(
					resourcer.getBonusAmount() + Double.valueOf(eshopConfigUtil.getParam(eshopConfigUtil.BONUS)));
			userDao.update(resourcer);
		}
		//对买家进行信息更新
		User updateUser = userDao.getByCondition(buyer);
		//更新本月消费信息，每个月最后一天这个字段进行归档，所以此处可以直接添加
		double amountThisMonth = updateUser.getAmountThisMonth() + order.getAmount();
		updateUser.setAmountThisMonth(amountThisMonth);
		//根据累积付款，更新级别
		double totalPay = updateUser.getTotalPay() + order.getAmount();
		updateUser.setTotalPay(totalPay);
		if (totalPay < 3000) {
			updateUser.setLevel(UserLevel.NORMAL.getCode());
		} else if (totalPay >= 3000 && totalPay < 8000) {
			updateUser.setLevel(UserLevel.GOLD.getCode());
			if (updateUser.getMembershipFirstDay() == null) {
				updateUser.setMembershipFirstDay(new Date());
			}
		} else if (totalPay >= 8000 && totalPay < 20000) {
			updateUser.setLevel(UserLevel.PLATINUM.getCode());
			if (updateUser.getMembershipFirstDay() == null) {
				updateUser.setMembershipFirstDay(new Date());
			}
		} else if (totalPay >= 20000) {
			updateUser.setLevel(UserLevel.DIAMOND.getCode());
			if (updateUser.getMembershipFirstDay() == null) {
				updateUser.setMembershipFirstDay(new Date());
			}
		}
		// 付款后，将积分添加至用户账户内
		updateUser.setPoints(updateUser.getPoints() + (int) (order.getAmount()));
		userDao.update(updateUser);
	}

}
