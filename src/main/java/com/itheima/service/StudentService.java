package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

public interface StudentService {
    Integer existsByClazzId(Integer clazzId);

    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void insert(Student student);
}
