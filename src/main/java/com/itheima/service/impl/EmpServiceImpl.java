package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> list = empMapper.page(empQueryParam);
        PageInfo<Emp> pageInfo = new PageInfo<>(list);
        return new PageResult<Emp>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            // 1.保存员工信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            // 2.保存员工工作经历
            List<EmpExpr> exprList = emp.getExprList();
            // 保证exprList不为空 如果用增强for循环以及list自带的isEmpty 会报空指针异常
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            // 3.保存员工操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工:" + emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional
    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            empMapper.deleteByIds(ids);
            empExprMapper.deleteByEmpIds(ids);
        }
    }

    @Override
    public void delete(Integer id) {
        empMapper.deleteByIds(Collections.singletonList(id));
        empExprMapper.deleteByEmpIds(Collections.singletonList(id));
    }

    @Transactional(readOnly = true)
    @Override
    public Emp findById(Integer id) {
        Emp emp = empMapper.findById(id);
        emp.setExprList(empExprMapper.findByEmpId(id));
        return emp;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        // 工作经历先删除再添加
        empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        return empMapper.selectAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        String jwt = null;
        if (e != null) {
            // 生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            jwt = JwtUtils.generateJwt(claims);
        }
        return e != null ? new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt) : null;
    }
}
