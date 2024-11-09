package pilot.instagram.global;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class Aop {

    @Pointcut("execution(* pilot.instagram.domain.user.UserController.*(..))")
    private void cut() {}

    @Around("cut()")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 메서드 정보 받아오기
        Method method = getMethod(proceedingJoinPoint);
        log.info("==================================================================================");
        log.info("method name = {} ", method.getName());

        // 파라미터 받아오기
        Object[] args = proceedingJoinPoint.getArgs();
        if (args.length == 0) log.info("no parameter");
        for (Object arg : args) {
            log.info("parameter type = {}", arg.getClass().getSimpleName());
            log.info("parameter value = {}", arg);
        }

        // 실행 시간 측정 시간
        long startTime = System.currentTimeMillis();

        // proceed()를 호출하여 실제 메서드 실행
        Object returnObj = proceedingJoinPoint.proceed();

        // 실행 시간 측정 종료
        long endTime = System.currentTimeMillis();
        double executionTIme = (endTime-startTime) / 1000.0;

        // 메서드의 리턴값 로깅
        log.info("return type = {}", returnObj.getClass().getSimpleName());
        log.info("return value = {}", returnObj);

        // 실행 시간 로깅
        log.info("execution time = {}s", executionTIme);
        log.info("==================================================================================");

        return returnObj;
    }

    private Method getMethod(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        return signature.getMethod();
    }
}
