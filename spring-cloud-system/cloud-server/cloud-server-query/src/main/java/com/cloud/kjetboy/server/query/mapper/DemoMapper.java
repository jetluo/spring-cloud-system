package com.cloud.kjetboy.server.query.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.kjetboy.server.common.exception.BusinessException;
import com.cloud.kjetboy.server.query.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author jet
 */
@Mapper
public interface DemoMapper extends BaseMapper<Demo>{
	/**
	 * 查询
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	public List<Demo> findAll(Map<String, Object> param) throws BusinessException;
	
	public void insertDemo(Map<String, Object> param) throws BusinessException;
	
	public void updateDemo(Map<String, Object> param) throws BusinessException;
	
	public void deleteDemo(Map<String, Object> param) throws BusinessException;
}
