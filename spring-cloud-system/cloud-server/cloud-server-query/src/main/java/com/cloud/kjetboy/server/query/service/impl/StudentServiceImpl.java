package com.cloud.kjetboy.server.query.service.impl;

import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.cloud.kjetboy.server.common.exception.BusinessException;
import com.cloud.kjetboy.server.query.entity.Student;
import com.cloud.kjetboy.server.query.repositories.StudentMapper;
import com.cloud.kjetboy.server.query.service.StudentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Author jet
 * @Date 2022/8/23 09:48
 * @Version 1.0
 **/
@Service
public class StudentServiceImpl  implements StudentService {
    protected final Log logger = LogFactory.getLog(getClass());


//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll(Map<String, Object> param) throws BusinessException {
        studentMapper.findStudentByName((String)param.get("name"));
        return null;
    }

    @Override
    public void insert(Map<String, Object> param) throws BusinessException {
       // Student student = BeanUtils.mapToBean(param, Student.class);
        Student student = new Student();
        student.setName("明明");
        student.setAddress("成都南充");
        student.setAge("18");
        List<String> color = new ArrayList<String>();
        color.add("红色");
        color.add("蓝色");
        student.setColorList(color);
        studentMapper.save(student);
    }

    @Override
    public void update(Map<String, Object> param) throws BusinessException {

    }

    @Override
    public void delete(Map<String, Object> param) throws BusinessException {
        Student student = BeanUtils.mapToBean(param, Student.class);
        studentMapper.delete(student);
    }
}
