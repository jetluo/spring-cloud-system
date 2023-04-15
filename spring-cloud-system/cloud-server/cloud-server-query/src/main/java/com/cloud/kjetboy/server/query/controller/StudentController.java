package com.cloud.kjetboy.server.query.controller;

import com.cloud.kjetboy.server.common.bean.RestResult;
import com.cloud.kjetboy.server.common.constants.CommonConstants;
import com.cloud.kjetboy.server.common.controller.BaseController;
import com.cloud.kjetboy.server.common.exception.BusinessException;
import com.cloud.kjetboy.server.query.entity.Student;
import com.cloud.kjetboy.server.query.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController{
	protected final static Log logger = LogFactory.getLog(StudentController.class);
	
	@Autowired
	private StudentService studentService;

	@RequestMapping("/aa")
	public RestResult aa(@RequestParam Map<String, Object> param) {

		return   RestResult.restSuccess(CommonConstants.SUCCESS_RESPONSE_CODE, "Spider启动成功");
	}
	//@Audit
	@GetMapping("/findAll")
	//@ApiOperation("查询所有数据")
	//@ApiImplicitParams({@ApiImplicitParam(name="param",value = "查询",dataType = "Map",required = false)})
	public RestResult findAll(@RequestParam  Map<String, Object> param ) {
		PageInfo<Student> pageInfo = new PageInfo<Student>();
		try {
			List<Student> list = studentService.findAll(param);
			pageInfo = new PageInfo<Student>(list);

		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return RestResult.restFail(CommonConstants.ERROR_RESPONSE_CODE, "xxx信息查询失败");
		}
		return RestResult.restSuccess(CommonConstants.SUCCESS_RESPONSE_CODE, pageInfo);
	}
	@RequestMapping("/insert")
	public RestResult insert(@RequestParam Map<String, Object> param) {
		try {
			studentService.insert(param);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			return RestResult.restFail(CommonConstants.ERROR_RESPONSE_CODE, "xxx信息查询失败");
		}
		return RestResult.restSuccess(CommonConstants.SUCCESS_RESPONSE_CODE, "xx");
	}
}
