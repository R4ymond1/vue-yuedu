package com.gedc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.gedc.dao.RedisMapper;
import com.gedc.entity.Department;
import com.gedc.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisMapper redisMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Department> selectDepartmentList() {
        // 从db中取得departmentList
        List<Department> departmentList = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            departmentList = redisMapper.selectDepartmentList();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        return departmentList;
    }

    @Override
    public List<Department> selectDepartmentListFromRedis() {
        // 从redis缓存中取得departmentList
        List<Department> departmentList = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            departmentList = redisTemplate.opsForList().range("departmentList", 0, -1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        return departmentList;
    }

}
