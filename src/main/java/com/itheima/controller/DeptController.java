package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping
    public Result list() {
//        System.out.println("查询所有部门信息");
        log.info("查询所有部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result deleteById(Integer id) {
//        System.out.println("删除部门信息：" + id);
        log.info("删除部门信息：" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Dept dept) {
//        System.out.println("新增部门：" + dept);
        log.info("新增部门：" + dept);
        deptService.addDept(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
//        System.out.println("查询部门信息：" + id);
        log.info("查询部门信息：" + id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门信息：" + dept);
        log.info("修改部门信息：" + dept);
        deptService.updateDept(dept);
        return Result.success();
    }
}
