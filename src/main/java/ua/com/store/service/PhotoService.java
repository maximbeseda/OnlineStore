package ua.com.store.service;

import org.springframework.web.multipart.MultipartFile;
import ua.com.store.model.Photo;
import ua.com.store.service.impl.PhotoServiceImpl;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link Photo}. Расширяет интерфейс {@link MainService}.
 *
 * @author Максим Беседа
 * @see Photo
 * @see MainService
 * @see PhotoServiceImpl
 */
public interface PhotoService extends MainService<Photo> {
    /**
     * Возвращает объект-изображение, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для возврата.
     * @return Объект класса {@link Photo} - объекта-изображение.
     */
    Photo get(String title);

    /**
     * Удаляет объект-изображение, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для удаления.
     */
    void remove(String title);

    /**
     * Сохраняет файл в файловой системе.
     *
     * @param photo Файл для сохранения.
     */
    void saveFile(MultipartFile photo);

    /**
     * Удаляет файл по url.
     *
     * @param url URL файла для удаления.
     */
    void deleteFile(String url);
}
