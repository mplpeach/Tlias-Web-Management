package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
