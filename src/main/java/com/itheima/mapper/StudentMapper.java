package com.itheima.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {
    /**
     * 根据班级id查询班级信息
     * @param clazzId 班级id
     */
    @Select("select 1 from student where clazz_id = #{clazzId} limit 1")
    Integer existsByClazzId(Integer clazzId);
}
