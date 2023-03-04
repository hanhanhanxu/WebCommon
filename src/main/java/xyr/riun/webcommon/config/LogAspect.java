package xyr.riun.webcommon.config;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import xyr.riun.webcommon.utils.ThreadLocalUtil;

/**
 * @author: HanXu
 * on 2021/6/11
 * Class description: 记录请求响应过程
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    /*@Pointcut("execution(* xyr.riun..*Controller..*(..))")
    public void pointcut() {}*/


    /**
     * 切点 Controller包下面所有类的所有方法
     */
    @Pointcut(value = "execution(* xyr.riun.webcommon.controller..*(..))")
    public void log() {}

    @Around(value = "log()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        try {
            log.info("外层请求类名: {}", proceedingJoinPoint.getTarget().getClass().getName());
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            log.info("外层请求方法: {}", methodSignature.getMethod().getName());

            Object[] args = proceedingJoinPoint.getArgs();
            for (Object o : args) {
                //不知道为啥确实有null进来了
                if (o == null) {
                    continue;
                }
                if (o instanceof String) {
                    log.debug("外层请求参数: {}", JSON.toJSON(o));
                } else if (o instanceof MultipartFile) {
                    log.debug("外层请求参数: {}", ((MultipartFile) o).getOriginalFilename());
                } else {
                    log.debug("外层请求参数: {} 未知的参数类型", o);
                }
            }

            long startTime = System.currentTimeMillis();
            // 执行方法
            result = proceedingJoinPoint.proceed();
            long endTime = System.currentTimeMillis();
            log.info("外层返回data: {}", JSON.toJSON(result));
            log.info("外层耗时: {}", endTime - startTime);

        } catch (Throwable e) {
            log.error("外层异常: {}", e.getMessage());
            // 继续抛出，否则不会将错误的响应返回出去
            throw e;
        } finally {
            //清除变量
            ThreadLocalUtil.clear();
            MDC.clear();
        }
        return result;
    }
}
