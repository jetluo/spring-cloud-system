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
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController{
	protected final static Log logger = LogFactory.getLog(DemoController.class);
	
	@Autowired
	private  DemoService demoService;

	@RequestMapping("/aa")
	public RestResult aa(@RequestParam Map<String, Object> param) {
		return   RestResult.restSuccess(CommonConstants.SUCCESS_RESPONSE_CODE, "Spider启动成功");
	}
	@RequestMapping("/spider")
	public RestResult spider(@RequestParam Map<String, Object> param) {
		Spider.create(new GithubRepoPageProcessor())
				//从https://github.com/code4craft开始抓
				.addUrl("https://github.com/code4craft")
				//设置Scheduler，使用Redis来管理URL队列
				.setScheduler(new RedisScheduler("localhost"))
				//设置Pipeline，将结果以json方式保存到文件
				.addPipeline(new JsonFilePipeline("/Users/jet/Downloads/workspace_dev"))
				//开启5个线程同时执行
				.thread(5)
				//启动爬虫
				.run();
		return   RestResult.restSuccess(CommonConstants.SUCCESS_RESPONSE_CODE, "Spider启动成功");
	}
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
