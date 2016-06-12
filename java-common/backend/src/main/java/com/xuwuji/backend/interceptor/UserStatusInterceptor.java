package com.xuwuji.backend.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.db.dao.UserDao;
import com.xuwuji.db.model.User;

/**
 * interceptor to check users' status
 * 
 * check them have already login
 * 
 * @author wuxu
 *
 */
public class UserStatusInterceptor implements HandlerInterceptor {

	private UserDao dao = new UserDao();

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getCharacterEncoding());
		Cookie[] cookies = request.getCookies();
		System.out.println("in the interceptor...");
		if (cookies != null) {
			System.out.println("checking cookie...");
			for (Cookie c : cookies) {
				System.out.println(c.getName());
				if (c.getName().equals("backend")) {
					String[] strs = c.getValue().split("-");
					String username = strs[0];
					String password = strs[1];
					System.out.println(username + " is logging...");
					// System.out.println(password);
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					if (dao.getId(username, password).size() != 0) {
						int id = dao.getId(username, password).get(0).getId();
						// System.out.println(id);
						user.setId(id);
						request.setAttribute("user", user);
					}
					break;
				}
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}