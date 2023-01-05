package pe.com.cronos.core.aspect.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Objects;

@Slf4j
@Aspect
public class LogExecutionAspect {

    @Around("@annotation(pe.com.cronos.core.aspect.log.LogExecution)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = methodSig.getName();
        try {
            log.info("Start, {}.{} <- {}", className, methodName, methodSig.getParameterNames());
            Object response = joinPoint.proceed();

            String output = "null";

            if (Objects.nonNull(response))
                output = response.getClass().getSimpleName();

            log.info("End, {}.{} -> {}", className, methodName, output);
            return response;
        } catch (Throwable e) {
            log.error("Error, {}.{}", className, methodName);
            throw e;
        }
    }
}
