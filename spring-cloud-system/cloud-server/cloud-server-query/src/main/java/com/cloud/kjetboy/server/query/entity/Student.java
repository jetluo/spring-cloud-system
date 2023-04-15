package com.cloud.kjetboy.server.query.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @ClassName Student
 * @Description 搜索引擎实体
 * @Author jet
 * @Date 2022/8/23 09:32
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "student")
public class Student {
    @Id
    @Field(store = true, type = FieldType.Keyword)
    private String id;

    @Field(store = true, type = FieldType.Keyword)
    private String name;

    @Field(store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String address;

    @Field(index = false, store = true, type = FieldType.Integer)
    private String age;

    @Field(index = false, store = true, type = FieldType.Date, format = DateFormat.basic_time)
    private String createTime;

    @Field(type = FieldType.Keyword)
    private String[] courseList;

    @Field(type = FieldType.Keyword)
    private List<String> colorList;


}
