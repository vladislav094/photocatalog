package guru.qa.photocatalog.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.plaf.PanelUI;
import java.util.Arrays;

@Aspect
public class MyAspect {

//    @Pointcut("@annotation(guru.qa.photocatalog.aspects.MyAnnotation)")
    @Pointcut("execution(public * guru.qa.photocatalog.controller.PhotoController.*(..))")
    public void printMethod() {}

    @Before("printMethod()")
    public void anyObj(){
        System.out.println("Bla bla bingz");
    }

    @Before("printMethod()")
    public void logMethod(JoinPoint joinPoint) {
//        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        String message = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            message += Arrays.toString(args);
        }
        log.info(message + "!");
    }
}
