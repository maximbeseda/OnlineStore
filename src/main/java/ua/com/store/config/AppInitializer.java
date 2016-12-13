package ua.com.store.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ua.com.store.listener.SessionListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Диспетчер Сервлета, который отвечает за инициализацию Spring MVC и меппинг URL.
 * Класс расширяет класс AbstractAnnotationConfigDispatcherServletInitializer.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Возвращает конфигурацию, в которой инициализируем ViewResolver.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * Возвращает конфигурации, которые инициализируют Beans.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class, SecurityConfig.class};
    }

    /**
     * Настроили мэпинг сервлета на "/" и поэтому все запросы будут перехвачены Диспетчером Сервлета Spring.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Настройка ссесии.
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        // Управление жизненным циклом корневого контекста приложения
        servletContext.addListener(new SessionListener());

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }

    /**
     * Включение исключений NoHandlerFound.
     */
    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext context) {
        final DispatcherServlet dispatcherServlet = (DispatcherServlet) super.createDispatcherServlet(context);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }
}
