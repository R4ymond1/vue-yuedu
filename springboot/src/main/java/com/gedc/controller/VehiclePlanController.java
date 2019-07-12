package com.gedc.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.gedc.config.VehicleApiUrlConfiguration;
import com.gedc.entity.Department;
import com.gedc.entity.Product;
import com.gedc.entity.VehicleClient;
import com.gedc.service.VehicleService;
import com.gedc.test.SpringELTest;

@RestController
@RequestMapping(value = "/api")
public class VehiclePlanController {

    int a = 0;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private SpringELTest springELTest;

    @Autowired
    private VehicleApiUrlConfiguration vehicleApiUrlConfiguration;

    @Autowired
    private RestTemplate restTemplate;

    // the api does't need localhost
    @RequestMapping(value = "/clientList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView queryVehicleClientList() {
        Map<String, Object> resultMap = new HashMap<>();
        List<VehicleClient> clientList = vehicleService.selectClientList();
        resultMap.put("clientList", clientList);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    @RequestMapping(value = "/productList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView queryProductList() {
        Map<String, Object> resultMap = new HashMap<>();
        List<Product> productList = vehicleService.selectProductList();
        resultMap.put("productList", productList);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    @RequestMapping(value = "/departmentList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView queryDepartmentList() {
        Map<String, Object> resultMap = new HashMap<>();
        List<Department> departmentList = vehicleService.selectDepartmentList();
        resultMap.put("departmentList", departmentList);
        System.out.println(
                "SpringEL:------------>name:" + springELTest.getName() + "  version:" + springELTest.getVersion());
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    @RequestMapping(value = "/getDepartment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView getDepartment(@RequestParam(value = "id") int id) {
        Map<String, Object> resultMap = new HashMap<>();
        Department department = vehicleService.getDepartment(id);
        resultMap.put("department", department);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    // test for spring-retry
    @RequestMapping(value = "/springRetryTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String springRetryTest() {
        try {
            int result = vehicleService.call();
            System.out.println(result);
        } catch (Exception e) {
        }
        return "hello spring-retry";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file) {
        try {
            FileUtils.writeByteArrayToFile(new File("d:/upload/" + file.getOriginalFilename()), file.getBytes());
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        }

    }

    @RequestMapping(value = "/departmentListRedis", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView queryDepartmentListFromRedis() {
        Map<String, Object> resultMap = new HashMap<>();
        List<Department> departmentList = vehicleService.selectDepartmentListByRedis();
        resultMap.put("departmentList", departmentList);
        return new ModelAndView(new MappingJackson2JsonView(), resultMap);
    }

    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String subscriptionHealthcheck() {
        a++;
        return "1234";
    }

    @RequestMapping(value = "/getStringFromVehicle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getStringFromVehicle() {
        // String url = vehicleApiUrlConfiguration.getVehicleStringUrl();
        String url = "http://127.0.0.1:8082/testapi/getString";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("X-recid", "000000000");
        HttpHeaders header = new HttpHeaders();
        // 需求需要传参为form-data格式
        header.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, header);
        String result = restTemplate.postForObject(url, httpEntity, String.class);
        // String result = response.getBody();
        System.err.println("==============>" + result);
        return result;
    }

}
