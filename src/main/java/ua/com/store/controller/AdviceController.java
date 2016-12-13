package ua.com.store.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.store.exception.BadRequestException;
import ua.com.store.exception.DuplicateException;
import ua.com.store.exception.ForbiddenException;
import ua.com.store.exception.WrongInformationException;
import ua.com.store.service.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;

/**
 * Класс - глобальный перехватчик исключений.
 * Он будет перехватывать исключения, которые не указаны в контроллере.
 * Методы класса работают с объектом, возвращенным handleRequest методом, является
 * типом {@link ModelAndView}, который агрегирует все параметры модели и имя отображения.
 * Этот тип представляет Model и View в MVC шаблоне.
 */
@ControllerAdvice
public class AdviceController {
    /**
     * Объект сервиса для работы с корзиной.
     */
    private ShoppingCartService shoppingCartService;

    /**
     * Объект для логирования информации.
     */
    private static final Logger logger = Logger.getLogger(AdviceController.class);

    /**
     * Конструктор для инициализации основных переменных класса-перехватчика исключений.
     * Помечен аннотацией @Autowired, которая позволит Spring автоматически инициализировать объекты.
     */
    @Autowired
    public AdviceController(ShoppingCartService shoppingCartService) {
        super();
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Перехват NoHandlerFoundException исключения (http статус 404).
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView noHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        return handleException(ex, request, "Ошибка 404. Не найдено!");
    }

    /**
     * Перехват BadRequestException исключения (http статус 400).
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView badRequestException(BadRequestException ex, HttpServletRequest request) {
        return handleException(ex, request, "Ошибка в запросе!");
    }

    /**
     * Перехват WrongInformationException исключения (http статус 400).
     */
    @ExceptionHandler(WrongInformationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView wrongInformationException(WrongInformationException ex, HttpServletRequest request) {
        return handleException(ex, request, "Ошибка в запросе!");
    }

    /**
     * Перехват ForbiddenException исключения (http статус 403).
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView forbiddenException(ForbiddenException ex, HttpServletRequest request) {
        return handleException(ex, request, "У Вас нет достаточных прав для доступа к этой странице.");
    }

    /**
     * Перехват DuplicateException исключения (http статус 409).
     */
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView duplicateException(DuplicateException ex, HttpServletRequest request) {
        return handleException(ex, request, "Такой объект уже существует!");
    }

    /**
     * Перехват всех остальных исключения (http статус 500).
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView otherException(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, "Временные неполадки с сервером... Приносим свои извинения!");
    }

    /**
     * Обработака всех входящих исключений: логирование, печать стека, возврат модели с информациею.
     */
    private ModelAndView handleException(Exception ex, HttpServletRequest request, String textError) {
        logger.error(request.getRemoteAddr() + " : " + request.getRequestURL());
        logger.error(ex.getMessage(), ex);
        ex.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.addObject("text_error", textError);
        modelAndView.setViewName("client/error");
        return modelAndView;
    }
}
