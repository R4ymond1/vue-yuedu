package com.gedc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.gedc.entity.Department;
import com.gedc.service.RedisService;

@RestController
@RequestMapping("/api")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/departmentListFromRedis", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView queryDepartmentListFromRedis() {
        Map<String, Object> resultMap = new HashMap<>();
        List<Department> departmentList = redisService.selectDepartmentListFromRedis();
        resultMap.put("departmentList", departmentList);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView test(@RequestBody String info) {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> departmentList = new ArrayList<>();
        resultMap.put("departmentList", departmentList);
        System.out.println(info);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    @RequestMapping(value = "/redisTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void redisTest() {
        // redis存储数据
        String key = "name";
        redisTemplate.opsForValue().set(key, "test1");
        // 获取数据
        String value = (String) redisTemplate.opsForValue().get(key);
        System.out.println("获取缓存中key为" + key + "的值为：" + value);
        // Department department = new Department();
        // Department department1 = new Department();
        // department.setId(1);
        // department.setName("CP");
        // department.setDescription("chinese platform");
        // department1.setId(2);
        // department1.setName("DX");
        // department1.setDescription("dx");
        // List<Department> departmentListTest = new ArrayList<>();
        // departmentListTest.add(department);
        // departmentListTest.add(department1);
        // // 将list存入redis
        // redisTemplate.opsForList().leftPush("departmentListTest", departmentListTest);
    }

}
