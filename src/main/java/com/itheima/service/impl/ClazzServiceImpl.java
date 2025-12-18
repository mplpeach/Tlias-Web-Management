package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.exception.ClazzHasStudentsException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import com.itheima.service.EmpService;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpService empService;
    @Autowired
    private StudentService studentService;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> list = clazzMapper.page(clazzQueryParam);
        list.forEach(clazz -> {
            Emp emp = empService.findById(clazz.getMasterId());
            clazz.setMasterName(emp.getName());
            LocalDate today = LocalDate.now();
            String status = "";
            if (clazz.getBeginDate().isAfter(today)) {
                status = "未开班";
            } else if (clazz.getEndDate().isBefore(today)) {
                status = "已结课";
            } else {
                status = "在读中";
            }
            clazz.setStatus(status);
        });
        PageInfo<Clazz> pageInfo = new PageInfo<>(list);
        return new PageResult<Clazz>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz findById(Integer id) {
        return clazzMapper.findById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    @Override
    public void deleteById(Integer id) {
        boolean exists = studentService.existsByClazzId(id) != null;
        if (exists) {
            throw new ClazzHasStudentsException();
        }
        clazzMapper.deleteById(id);
    }
}
