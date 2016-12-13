package ua.com.store.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.store.dao.ShoppingCartDao;
import ua.com.store.model.SalePosition;
import ua.com.store.model.ShoppingCart;

import java.util.List;

/**
 * Класс реализует методы интерфейса {@link ShoppingCartDao} для работы с корзиной.
 */
@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    /**
     * Объект корзина, в которой хранятся торговые позиции клиента.
     */
    private ShoppingCart shoppingCart;

    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param shoppingCart Объект класса {@link ShoppingCart} для работы с товарной корзиной.
     */
    @Autowired
    public ShoppingCartDaoImpl(ShoppingCart shoppingCart) {
        super();
        this.shoppingCart = shoppingCart;
    }

    /**
     * Возвращает список всех торговых позиций в корзине.
     */
    @Override
    public List<SalePosition> getSalePositions() {
        return shoppingCart.getSalePositions();
    }

    /**
     * Добавляет торговую позицию в список корзины.
     */
    @Override
    public void addSalePosition(SalePosition salePosition) {
        shoppingCart.addSalePosition(salePosition);
    }

    /**
     * Удаляет торговую позицию из корзины.
     */
    @Override
    public void removeSalePosition(SalePosition salePosition) {
        shoppingCart.removeSalePosition(salePosition);
    }

    /**
     * Очищает корзину. Удаляет все торговые позиции в корзине.
     */
    @Override
    public void clearSalePositions() {
        shoppingCart.clearSalePositions();
    }

    /**
     * Возвращает объект-корзину целиком.
     */
    @Override
    public ShoppingCart get() {
        return shoppingCart;
    }

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     */
    @Override
    public int getSize() {
        return shoppingCart.getSize();
    }

    /**
     * Возвращает цену корзины - цена всех продаж.
     */
    @Override
    public double getPrice() {
        return shoppingCart.getPrice();
    }
}
