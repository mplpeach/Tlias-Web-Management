package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 分页查询班级信息
     * @param clazzQueryParam 查询参数
     */
    List<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 新增班级信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 设置主键回填
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    /**
     * 根据id查询班级信息
     */
    @Select("select * from clazz where id = #{id}")
    Clazz findById(Integer id);

    /**
     * 根据id修改班级信息
     */
    void updateById(Clazz clazz);

    /**
     * 根据id删除班级信息
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);
}
