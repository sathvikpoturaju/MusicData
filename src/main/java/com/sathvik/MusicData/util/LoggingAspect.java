package com.sathvik.MusicData.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    public static final String pointcut = "execution(* com.sathvik.MusicData.service.impl.*Impl.*(..))";

    @AfterThrowing(pointcut = pointcut, throwing = "e")
    public void logServiceException(Exception e) {
        log.error(e.getMessage(), e);
    }
}