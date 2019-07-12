package com.gedc.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gedc.entity.Department;

public class JacksonTest {

    // java to json 序列化
    public static void java2Json() throws JsonProcessingException {
        Department department = new Department();
        department.setId(25);
        department.setName("pf");
        department.setDescription("private field");

        ObjectMapper objectMapper = new ObjectMapper();

        // department 转json
        String json = objectMapper.writeValueAsString(department);
        System.out.println(json);

        // list集合转json
        List<Department> result = new ArrayList<>();
        result.add(department);
        String jsonList = objectMapper.writeValueAsString(result);
        System.out.println(jsonList);
    }

    // json to java 反序列化
    public static void json2Java() throws JsonParseException, JsonMappingException, IOException {
        String json = "{\"id\":24,\"myName\":\"sm\",\"description\":\"senior manager\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        // 将json和java bean映射
        Department department = objectMapper.readValue(json, Department.class);
        System.out.println(department.toString());

        // 将json读成一个Map
        JavaType javaType = objectMapper.getTypeFactory().constructType(Map.class);
        Map<String, Object> getData = objectMapper.readValue(json, javaType);
        System.out.println("将json读成一个Map");
        for (Map.Entry<String, Object> entrySet : getData.entrySet()) {
            System.out.println(entrySet);
        }

    }

    public static void main(String[] args) throws IOException {
        System.out.println("序列化");
        java2Json();
        System.out.println("反序列化");
        json2Java();
    }
}
