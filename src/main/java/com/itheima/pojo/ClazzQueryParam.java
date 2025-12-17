package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzQueryParam {
    private String name; // 班级名称
    private LocalDate begin;// 班级开始时间
    private LocalDate end;// 班级结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Integer page = 1;// 当前页码
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Integer pageSize = 10;// 每页显示记录数
}
