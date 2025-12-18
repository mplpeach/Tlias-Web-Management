package com.itheima.service.impl;

import com.itheima.mapper.StudentMapper;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Integer existsByClazzId(Integer clazzId) {
        return studentMapper.existsByClazzId(clazzId);
    }
}
