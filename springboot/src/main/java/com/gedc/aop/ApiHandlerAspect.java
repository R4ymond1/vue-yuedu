package com.gedc.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * コントローラー呼出前後の共通処理.
 */
@Aspect
@Component
public class ApiHandlerAspect {

    /**
     * ログ.
     */
    private Logger logger = LogManager.getLogger(ApiHandlerAspect.class.getName());

    /**
     * 共通処理を抜き出したい横断条件.
     */
    private static final String REQUEST_MAPPING = "@annotation(org.springframework.web.bind.annotation.RequestMapping) && !@annotation(jp.ne.internavi.cloud.wifi.common.annotation.HealthCheck)";

    /**
     * 指定するアノテーションを付けるメソッドを呼出する時、共通処理を抜き出す.
     */
    @Pointcut(REQUEST_MAPPING)
    private void annotationPointCut() {
    }

    /**
     * コントローラーの前処理.
     *
     * @param joinPoint JoinPoint
     */
    @Before("annotationPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        if (joinPoint != null && joinPoint.getSignature() != null) {
            logger.info("method {}.{} begining ...", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
        }
    }

    /**
     * コントローラーの後処理.
     *
     * @param joinPoint JoinPoint
     */
    @After("annotationPointCut()")
    public void doAfter(JoinPoint joinPoint) {
        if (joinPoint != null && joinPoint.getSignature() != null) {
            logger.info("method {}.{} end", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
        }
    }

    /**
     * エラー処理.
     *
     * @param proceedingJoinPoint ProceedingJoinPoint
     * @return 戻り値
     * @throws Throwable エラー
     */
    @Around("annotationPointCut()")
    public Object handleThrowing(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed();
    }

    /**
     * コントローラーの最後処理（doAfter()より遅い）.
     *
     * @param object 戻り値
     * @return 戻り値
     */
    @AfterReturning(pointcut = "annotationPointCut()", returning = "object")
    public Object doAfterReturning(Object object) {
        if (object != null) {
            try {
                if (object instanceof ModelAndView) {
                    ModelMap map = ((ModelAndView) object).getModelMap();
                    logger.info("response content is {}", new ObjectMapper().writeValueAsString(map));
                } else {
                    logger.info("response content is {}", new ObjectMapper().writeValueAsString(object));
                }
            } catch (JsonProcessingException e) {
                // do nothing
            }
        }
        return object;
    }
}
