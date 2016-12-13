package ua.com.store.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Класс наследующий AbstractSecurityWebApplicationInitializer.
 * Класс нужен для того, чтобы удостовериться, что настройки безопасности включены
 * в основной контекст приложения.
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
