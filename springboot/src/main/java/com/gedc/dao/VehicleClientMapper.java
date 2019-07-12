package com.gedc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gedc.entity.Department;
import com.gedc.entity.Product;
import com.gedc.entity.VehicleClient;

@Mapper
public interface VehicleClientMapper {

    public List<VehicleClient> selectVehicleClientList();

    public List<Product> selectProductList();

    public List<Department> selectDepartmentList();

    public Department getDepartment(int id);

    public int insert(Department record);

}
