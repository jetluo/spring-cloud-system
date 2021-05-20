package com.cloud.kjetboy.server.audit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.kjetboy.server.common.exception.BusinessException;
import com.cloud.kjetboy.server.audit.entity.Demo;

import java.util.List;
import java.util.Map;

public interface DemoService extends IService<Demo>{
	//查询Demo列表
  public List<Demo> findAll(Map<String, Object> param) throws BusinessException;
		
  //新增jar包
  public void insertDemo(Map<String, Object> param) throws BusinessException;	
  
  //修改jar包
  public void updateDemo(Map<String, Object> param) throws BusinessException;
	
	//删除jar包
  public void deleteDemo(Map<String, Object> param) throws BusinessException;
}
