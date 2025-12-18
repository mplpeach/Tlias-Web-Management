package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 根据班级id查询班级信息
     * @param clazzId 班级id
     */
    @Select("select 1 from student where clazz_id = #{clazzId} limit 1")
    Integer existsByClazzId(Integer clazzId);

    /**
     * 分页查询学生信息
     * @param studentQueryParam 学生查询参数
     */
    List<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 新增学生信息
     * @param student 学生信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 设置主键回填
    void insert(Student student);

    /**
     * 根据id查询学生信息
     * @param id 学生id
     */
    @Select("select * from student where id = #{id}")
    Student findById(Integer id);
}
