package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result list(StudentQueryParam studentQueryParam) {
        log.info("分页查询，参数：{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学生，数据：{}", student);
        studentService.insert(student);
        return Result.success();
    }

    @GetMapping("{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("查询学生，id：{}", id);
        Student student = studentService.findById(id);
        return Result.success(student);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("更新学生，数据：{}", student);
        studentService.update(student);
        return Result.success();
    }

    @Log
    @DeleteMapping("{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("删除学生，ids：{}", ids);
        String[] split = ids.split(",");
        List<Integer> list = Arrays.stream(split).map(Integer::parseInt).toList();
        studentService.deleteByIds(list);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result addViolation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学生{},增加违纪次数,扣分:{}", id, score);
        studentService.addViolation(id, score);
        return Result.success();
    }
}
