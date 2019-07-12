package com.gedc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gedc.controller.BaseController;

@Configuration
@Component
public class ApiRequestInterceptor implements HandlerInterceptor {

    @Value("${spring.application.name}")
    public String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startTime = System.currentTimeMillis();
        String url = request.getRequestURI();
        String clientId = request.getHeader("aa");
        String userId = "aa";
        request.setAttribute("startTime", startTime);
        BaseController baseController = (BaseController) ((HandlerMethod) handler).getBean();
        baseController.setUserId(userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();
        // System.out.println("本次请求处理时间为：" + new Long(endTime - startTime));
    }

}
