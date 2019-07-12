package com.gedc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gedc.entity.Department;

@Mapper
public interface RedisMapper {

    public List<Department> selectDepartmentList();

}
