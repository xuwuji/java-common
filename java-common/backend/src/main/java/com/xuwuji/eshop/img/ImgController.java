package com.xuwuji.eshop.img;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/img")
public class ImgController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/eshop/img");
	}
	
	@RequestMapping(value = "/uploadImg", method = { RequestMethod.POST })
	public @ResponseBody String uploadImg(HttpServletRequest request, @RequestParam(value = "img") MultipartFile pic) {
		// ��ȡͼƬԭʼ�ļ���
		String originalFileName = pic.getOriginalFilename();
		String name = "" + System.currentTimeMillis();
		// ��ȡ��׺
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
		// ���·��
		String path = "/WEB-INF/sources/upload/" + name + extension;
		// ����·��
		String url = request.getSession().getServletContext().getRealPath("") + path;
		// ��վ·��
		String webUrl = request.getRequestURL() + path;
		webUrl = webUrl.replaceAll("http://", "").replaceAll("/uploadImg/WEB-INF", "");
		webUrl = "{\"webUrl\":\"" + webUrl + "\",\"path\":\"/sources/upload/" + name + extension + "\"}";
		File dir = new File(url);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// �ϴ�ͼƬ
		try {
			pic.transferTo(new File(url));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return webUrl;
	}
}
