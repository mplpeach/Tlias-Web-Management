package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询，参数：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("新增班级，数据：{}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("{id}")
    public Result findById(@PathVariable Integer id){
        log.info("查询班级，id：{}", id);
        Clazz clazz = clazzService.findById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("更新班级，数据：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }
}
