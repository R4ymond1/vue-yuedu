package com.gedc.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.gedc.dao.VehicleClientMapper;
import com.gedc.entity.Department;
import com.gedc.entity.Product;
import com.gedc.entity.VehicleClient;
import com.gedc.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleClientMapper vehicleClientMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<VehicleClient> selectClientList() {
        List<VehicleClient> list = vehicleClientMapper.selectVehicleClientList();
        // redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(list));
        // redisTemplate.opsForList().leftPush("vehicleClient", list);
        return vehicleClientMapper.selectVehicleClientList();
    }

    @Override
    public List<Product> selectProductList() {
        return vehicleClientMapper.selectProductList();
    }

    @Override
    public List<Department> selectDepartmentList() {
        List<Department> departmentList = vehicleClientMapper.selectDepartmentList();
        // 将list存入redis
        // redisTemplate.opsForList().leftPush("departmentList", departmentList);
        return departmentList;
    }

    @Override
    public Department getDepartment(int id) {
        return vehicleClientMapper.getDepartment(id);
    }

    @Override
    @Retryable(value = {
            RemoteAccessException.class }, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public int call() throws Exception {

        /*
         * Department department = new Department();
         * department.setId(5);
         * department.setName("IV");
         * department.setDescription("Inteligent Vehicle");
         * int result = vehicleClientMapper.insert(department);
         */
        Random random = new Random();
        int id = random.nextInt(10);
        if (id != 5) {
            System.err.println(LocalTime.now() + " do something...");
            throw new RemoteAccessException("RPC调用异常");
        } else {
            return 1;
        }
    }

    @Override
    @Recover
    public int recover(RemoteAccessException e) {
        System.err.println(e.getMessage());
        return 2;
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // @Scheduled(fixedRate = 5000)
    // public void reportCurrentTime() {
    // System.out.println("每隔五秒执行一次" + dateFormat.format(new Date()));
    //
    // }

    @Override
    public List<Department> selectDepartmentListByRedis() {
        // 取得redis缓存中的list
        List<Department> departmentList = redisTemplate.opsForList().range("departmentList", 0, -1);
        return departmentList;
    }
}
