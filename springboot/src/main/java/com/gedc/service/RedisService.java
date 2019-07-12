package com.gedc.service;

import java.util.List;

import com.gedc.entity.Department;

public interface RedisService {

    public List<Department> selectDepartmentList();

    public List<Department> selectDepartmentListFromRedis();

}
