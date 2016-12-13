package ua.com.store.service;

import org.springframework.web.multipart.MultipartFile;
import ua.com.store.model.Photo;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Photo}. Расширяет интерфейс {@link MainService}.
 */
public interface PhotoService extends MainService<Photo> {
    /**
     * Возвращает объект-изображение, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    Photo get(String title);

    /**
     * Удаляет объект-изображение, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    void remove(String title);

    /**
     * Сохраняет файл в файловой системе.
     */
    void saveFile(MultipartFile photo);

    /**
     * Удаляет файл по url.
     */
    void deleteFile(String url);
}
