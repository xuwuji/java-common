package com.xuwuji.eshop.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.xuwuji.eshop.db.mapper.OrderMapper;
import com.xuwuji.eshop.db.util.SessionFactory;
import com.xuwuji.eshop.model.Order;

@Component
public class OrderDao {

	public Order add(Order order) {
		SqlSession session = SessionFactory.openDEVSession();
		try {
			OrderMapper mapper = session.getMapper(OrderMapper.class);
			mapper.add(order);
			session.commit();
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return order;
	}

	public void update(HashMap<String, Object> map) {
		SqlSession session = SessionFactory.openDEVSession();
		try {
			System.out.print("here");
			OrderMapper mapper = session.getMapper(OrderMapper.class);
			mapper.update(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}

	public List<Order> getAllByWechatIdAndStatus(String wechatId, String state) {
		SqlSession session = SessionFactory.openDEVSession();
		List<Order> result = new ArrayList<Order>();
		try {
			OrderMapper mapper = session.getMapper(OrderMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("wechatId", wechatId);
			map.put("state", state);
			result = mapper.getAllByWechatIdAndState(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	public List<Order> getAllByOpenIdAndStatus(String openId, String state) {
		SqlSession session = SessionFactory.openDEVSession();
		List<Order> result = new ArrayList<Order>();
		try {
			OrderMapper mapper = session.getMapper(OrderMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("openId", openId);
			map.put("state", state);
			result = mapper.getAllByOpenIdAndState(map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	public Order getOrderInfoByOrderId(String orderId) {
		SqlSession session = SessionFactory.openDEVSession();
		List<Order> result = new ArrayList<Order>();
		try {
			OrderMapper mapper = session.getMapper(OrderMapper.class);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			result = mapper.getOrderInfoByOrderId(map);
			session.commit();
			return result.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return result.get(0);
	}

}
