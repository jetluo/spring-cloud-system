package com.cloud.kjetboy.server.crawler.controller;

import com.github.pagehelper.PageInfo;
import com.cloud.kjetboy.server.common.bean.RestResult;
import com.cloud.kjetboy.server.common.constants.CommonConstants;
import com.cloud.kjetboy.server.common.controller.BaseController;
import com.cloud.kjetboy.server.common.exception.BusinessException;
import com.cloud.kjetboy.server.crawler.entity.Demo;
import com.cloud.kjetboy.server.crawler.service.DemoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController{
	protected final static Log logger = LogFactory.getLog(DemoController.class);
	
	@Autowired
	private  DemoService demoService;


	@RequestMapping("/findAll")
	public RestResult findAll(@RequestParam Map<String, Object> param) {
		PageInfo<Demo> pageInfo = new PageInfo<Demo>();
		try {

			List<Demo> list = demoService.findAll(param);
			
			pageInfo = new PageInfo<Demo>(list);

		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return RestResult.restFail(CommonConstants.ERROR_RESPONSE_CODE, "xxx信息查询失败");
		}
		return RestResult.restSuccess(CommonConstants.SUCCESS_RESPONSE_CODE, pageInfo);
	}
	@RequestMapping("/insert")
	public RestResult insert(@RequestParam Map<String, Object> param) {
		
		try {
			demoService.insertDemo(param);
			
			

		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return RestResult.restFail(CommonConstants.ERROR_RESPONSE_CODE, "xxx信息查询失败");
		}
		return RestResult.restSuccess(CommonConstants.SUCCESS_RESPONSE_CODE, "xx");
	}
}
