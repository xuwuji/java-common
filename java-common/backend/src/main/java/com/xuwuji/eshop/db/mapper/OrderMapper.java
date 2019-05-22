package com.xuwuji.eshop.db.mapper;

import java.util.HashMap;
import java.util.List;

import com.xuwuji.eshop.model.Order;

public interface OrderMapper {

	public void add(Order order);

	public void update(HashMap<String, Object> map);

	public void updateMemo(HashMap<String, Object> map);
	
	public void updateState(HashMap<String, Object> map);

	public void updateTransactionId(HashMap<String, Object> map);

	public void updatePrepayId(HashMap<String, Object> map);

	public List<Order> getAllByWechatIdAndState(HashMap<String, Object> map);

	public List<Order> getAllByOpenIdAndState(HashMap<String, Object> map);

	public List<Order> getAllByOpenId(HashMap<String, Object> map);

	public List<Order> getOrderInfoByOrderId(HashMap<String, Object> map);

	public List<Order> getAll();

	public void updateBalanceAmount(HashMap<String, Object> map);

}
