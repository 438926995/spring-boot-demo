package com.app.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author huwenwen
 * @since 17/1/5
 */
@Aspect
@Component
@Log4j
public class WebLogAspect {

  @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public void webLog(){

  }

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    // 记录下请求内容
    log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

  }

  @AfterReturning(returning = "ret", pointcut = "webLog()")
  public void doAfterReturning(Object ret) throws Throwable {
    // 处理完请求，返回内容
    log.info("RESPONSE : " + ret);
  }

}
