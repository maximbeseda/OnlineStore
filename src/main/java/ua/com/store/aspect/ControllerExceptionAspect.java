package ua.com.store.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Класс реализует сквозную функциональность, а именно перехват исключений.
 * Помечен аннотациями @Aspect - аспект изменяет поведение остального кода, применяя совет в точках соединения.
 * Помечен аннотациями @Component указывает, что клас является компонентом фреймворка Spring.
 */
@Component
@Aspect
public class ControllerExceptionAspect {
    /**
     * Объект для логирования информации.
     */
    private static Logger logger = Logger.getLogger(ControllerExceptionAspect.class);

    /**
     * Перехватывает исключения. Метод будет вызываться в случае появления исключительных
     * ситуаций, логирует информацию об исключении.
     */
    @AfterThrowing(pointcut = "execution(* ua.com.store..controller..*(..))", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        logger.error("EXCEPTION IN METHOD -> " + exception.getClass());
    }
}
