package com.cloud.kjetboy.server.query.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.kjetboy.server.common.exception.BusinessException;
import com.cloud.kjetboy.server.query.entity.Student;

import java.util.List;
import java.util.Map;


public interface StudentService {
    //查询Demo列表
    public List<Student> findAll(Map<String, Object> param) throws BusinessException;

    //新增
    public void insert(Map<String, Object> param) throws BusinessException;

    //修改
    public void update(Map<String, Object> param) throws BusinessException;

    //删除
    public void delete(Map<String, Object> param) throws BusinessException;
}
