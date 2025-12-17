package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    /**
     * 分页查询员工信息
     * @param empQueryParam 查询参数
     * @return
     */
    List<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 保存员工信息
     * @param emp 员工信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 设置主键回填
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
    "values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    @Select("select * from emp where id = #{id}")
    Emp findById(Integer id);

    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     */
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 查询所有员工信息
     */
    @Select("select * from emp")
    List<Emp> list();
}
