package ua.com.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.store.dao.ShoppingCartDao;
import ua.com.store.exception.BadRequestException;
import ua.com.store.model.SalePosition;
import ua.com.store.model.ShoppingCart;
import ua.com.store.service.ShoppingCartService;

import java.util.List;

/**
 * Класс сервисного слоя для работы с торговой корзиной.
 * Реализует методы интерфейса {@link ShoppingCartService}.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Максим Беседа
 * @see ShoppingCart
 * @see ShoppingCartService
 * @see ShoppingCartDao
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    /**
     * Реализация интерфейса для работы з торговой корзиной.
     */
    private ShoppingCartDao shoppingCartDAO;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring автоматически инициализировать объект.
     *
     * @param shoppingCartDAO Реализация интерфейса для работы з торговой корзиной.
     */
    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    /**
     * Возвращает объект корзину. Режим только для чтения.
     *
     * @return Объект класса {@link ShoppingCart} - торговая корзина.
     * @throws BadRequestException Бросает исключение, если корзина отсутствует.
     */
    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCart() throws BadRequestException {
        ShoppingCart shoppingCart = shoppingCartDAO.get();
        if (shoppingCart == null) {
            throw new BadRequestException("Can't find shopping cart!");
        }
        return shoppingCart;
    }

    /**
     * Добавляет торговую позицию в список корзины.
     *
     * @param salePosition Торговая позиция, которая будет добавлена в корзину.
     */
    @Override
    @Transactional
    public void add(SalePosition salePosition) {
        if (salePosition != null) {
            shoppingCartDAO.addSalePosition(salePosition);
        }
    }

    /**
     * Возвращает список всех торговых позиций в корзине. Режим только для чтения.
     *
     * @return Объект типа {@link List} - список торговых позиций.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SalePosition> getSalePositions() {
        return shoppingCartDAO.getSalePositions();
    }

    /**
     * Удаляет торговую позицию из корзины.
     *
     * @param salePosition Торговая позиция для удаления из корзины.
     */
    @Override
    @Transactional
    public void remove(SalePosition salePosition) {
        if (salePosition != null) {
            shoppingCartDAO.removeSalePosition(salePosition);
        }
    }

    /**
     * Очищает корзину. Удаляет все торговые позиции в корзине.
     */
    @Override
    @Transactional
    public void clear() {
        shoppingCartDAO.clearSalePositions();
    }

    /**
     * Возвращает цену корзины - цена всех продаж. Режим только для чтения.
     *
     * @return Значение типа double - цена корзины.
     */
    @Override
    @Transactional(readOnly = true)
    public double getPrice() {
        return shoppingCartDAO.getPrice();
    }

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     * Режим только для чтения.
     *
     * @return Значение типа int - количество товаров в корзине.
     */
    @Override
    @Transactional(readOnly = true)
    public int getSize() {
        return shoppingCartDAO.getSize();
    }
}
