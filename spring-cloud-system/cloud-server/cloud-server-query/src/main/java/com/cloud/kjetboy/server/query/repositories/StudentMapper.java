package com.cloud.kjetboy.server.query.repositories;

import com.cloud.kjetboy.server.query.entity.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author jet
 * @Description //学生存储实体类
 * @Date 2022/8/23
 * @Param
 * @return
 **/
@Repository
public interface StudentMapper extends  ElasticsearchRepository<Student,String> {

    List<Student> findStudentByName(String name);
}
