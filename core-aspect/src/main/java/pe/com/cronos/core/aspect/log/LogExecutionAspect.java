package pe.com.cronos.core.aspect.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LogExecutionAspect {

    @Around("@annotation(pe.com.cronos.core.aspect.log.LogExecution)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getCanonicalName();
        String methodName = joinPoint.getSignature().getName();
        try {
            log.info("Start, {}.{}", className, methodName);
            Object response = joinPoint.proceed();
            log.info("End, {}.{}", className, methodName);
            return response;
        } catch (Throwable e) {
            log.error("Error, {}.{}", className, methodName);
            throw e;
        }
    }
}
