package com.gedc.service;

import java.util.List;

import org.springframework.remoting.RemoteAccessException;

import com.gedc.entity.Department;
import com.gedc.entity.Product;
import com.gedc.entity.VehicleClient;

public interface VehicleService {
    public List<VehicleClient> selectClientList();

    public List<Product> selectProductList();

    public List<Department> selectDepartmentList();

    public Department getDepartment(int id);

    public int call() throws Exception;

    int recover(RemoteAccessException e);

    public List<Department> selectDepartmentListByRedis();

}
