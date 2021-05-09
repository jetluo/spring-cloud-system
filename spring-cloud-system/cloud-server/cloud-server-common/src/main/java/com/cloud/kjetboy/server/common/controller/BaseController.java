package com.cloud.kjetboy.server.common.controller;


import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class BaseController {

	/**
	 * 根据token获取用户信息
	 */
	public Map<String, Object> getUserByToken(HttpServletRequest request){
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}
}
