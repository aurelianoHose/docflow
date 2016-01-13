package com.netradio.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfileAspect {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.netradio.service.*ServiceImpl.*(..))")
    public void profile() {
    }

    @Pointcut("execution(* com.netradio.service.FlowService.getImage(..)) && args(java.lang.Long)")
    public void image() {
    }

    /*
     * @Around("profile()") public Object execute(ProceedingJoinPoint point)
     * throws Throwable { long time = System.nanoTime(); Object obj =
     * point.proceed(); time = System.nanoTime() - time;
     * log.info("!!! Method: {} Time: {}", point.getSignature().toShortString(),
     * time); return obj; }
     */

    /*
     * @Around("image()") public Object execute2(ProceedingJoinPoint point)
     * throws Throwable { Object[] args = point.getArgs(); args[0] =
     * Long.valueOf(4L); Object obj = point.proceed(args); return obj; }
     */
}
