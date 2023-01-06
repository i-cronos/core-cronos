package pe.com.cronos.core.aspect.time;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Objects;

@Slf4j
public class LogTimeAspect {

    @Around("@annotation(pe.com.cronos.core.aspect.time.LogTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
        long start = System.currentTimeMillis();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = methodSig.getName();
        try {
            log.info("Start, {}.{} <- {}", className, methodName, methodSig.getParameterNames());
            Object response = joinPoint.proceed();

            String output = "null";

            if (Objects.nonNull(response))
                output = response.getClass().getSimpleName();

            log.info("End, {}.{} -> {} , time: {}", className, methodName, output, (System.currentTimeMillis() - start));
            return response;
        } catch (Throwable e) {
            log.error("Error, {}.{}, time: {}", className, methodName, (System.currentTimeMillis() - start));
            throw e;
        }
    }
}
