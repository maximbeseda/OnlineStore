package ua.com.store.service;

import ua.com.store.model.Order;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Интерфейс сервисного слоя для работы с электронной почтой.
 * Представляет методы для отправки сообщений на электронную почту.
 */
public interface SenderService {
    /**
     * Отсылает информацию о заказе менеджерам на электронную почту.
     */
    void send(Order order);

    /**
     * Возвращает настройки протокола TLS (Transport Layer Security) для отправки сообщения.
     */
    Properties getTLSProperties();

    /**
     * Возвращает настройки протокола SSL для отправки сообщения.
     */
    Properties getSSLProperties();

    /**
     * Отправляет сообщение по заданым параметрам.
     *
     * @param properties Настройки протокола для сессии.
     * @param toEmail    Адрес электронной почты, на который будет отправлено сообщение.
     * @param subject    Тема сообщения.
     * @param text       Текст сообщения.
     * @throws MessagingException           Исключение класса InternetAddress.
     * @throws UnsupportedEncodingException Исключение кодировки метдом MimeUtility.encodeText().
     */
    void sendMessage(Properties properties, String toEmail, String subject, String text) throws MessagingException, UnsupportedEncodingException;
}
