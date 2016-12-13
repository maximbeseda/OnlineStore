package ua.com.store.dao;

import ua.com.store.model.SalePosition;
import ua.com.store.model.ShoppingCart;

import java.util.List;

/**
 * Интерфейс описывает набор методов для работы объектов класса {@link ShoppingCart}.
 */
public interface ShoppingCartDao {
    /**
     * Возвращает список всех торговых позиций в корзине.
     */
    List<SalePosition> getSalePositions();

    /**
     * Добавляет торговую позицию в список корзины.
     */
    void addSalePosition(SalePosition salePosition);

    /**
     * Удаляет торговую позицию из корзины.
     */
    void removeSalePosition(SalePosition salePosition);

    /**
     * Очищает корзину. Удаляет все торговые позиции в корзине.
     */
    void clearSalePositions();

    /**
     * Возвращает объект-корзину целиком.
     */
    ShoppingCart get();

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     */
    int getSize();

    /**
     * Возвращает цену корзины - цена всех торговых позиций.
     */
    double getPrice();
}
