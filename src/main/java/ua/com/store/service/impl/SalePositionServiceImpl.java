package ua.com.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.store.dao.SalePositionDAO;
import ua.com.store.model.SalePosition;
import ua.com.store.service.SalePositionService;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link SalePosition}
 * в базе данных интерфейса {@link SalePositionService}, наследует родительский
 * класс {@link MainServiceImpl}, в котором реализованы основные методы.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 */
@Service
public class SalePositionServiceImpl extends MainServiceImpl<SalePosition> implements SalePositionService {

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    public SalePositionServiceImpl(SalePositionDAO dao) {
        super(dao);
    }
}
