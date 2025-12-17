package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 分页查询班级信息
     * @param clazzQueryParam 查询参数
     */
    List<Clazz> page(ClazzQueryParam clazzQueryParam);
}
