package com.cloud.kjetboy.server.audit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.kjetboy.server.common.exception.BusinessException;
import com.cloud.kjetboy.server.audit.entity.Demo;
import com.cloud.kjetboy.server.audit.mapper.DemoMapper;
import com.cloud.kjetboy.server.audit.service.DemoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper,Demo> implements DemoService {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private DemoMapper demoMapper;
	

	@Override
	public void insertDemo(Map<String, Object> param)  throws BusinessException {
		demoMapper.insertDemo(param);
	}

	@Override
	public void updateDemo(Map<String, Object> param)  throws BusinessException {
		demoMapper.updateDemo(param);
	}

	@Override
	public void deleteDemo(Map<String, Object> param)  throws BusinessException {
		demoMapper.deleteDemo(param);
	}

	@Override
	public List<Demo> findAll(Map<String, Object> param) throws BusinessException {
	
		return demoMapper.findAll(param);
	}

	
}
