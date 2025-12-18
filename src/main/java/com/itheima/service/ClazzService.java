package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;

public interface ClazzService {
    /**
     * 分页查询
     * @param clazzQueryParam 查询参数
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void save(Clazz clazz);

    Clazz findById(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);

}
