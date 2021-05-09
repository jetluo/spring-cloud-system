package com.cloud.kjetboy.server.crawler.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.kjetboy.server.common.exception.BusinessException;
import com.cloud.kjetboy.server.crawler.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DemoMapper extends BaseMapper<Demo>{
	
	public List<Demo> findAll(Map<String, Object> param) throws BusinessException;
	
	public void insertDemo(Map<String, Object> param) throws BusinessException;
	
	public void updateDemo(Map<String, Object> param) throws BusinessException;
	
	public void deleteDemo(Map<String, Object> param) throws BusinessException;
}
