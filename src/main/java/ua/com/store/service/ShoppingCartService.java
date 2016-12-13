package ua.com.store.service;

import ua.com.store.model.SalePosition;
import ua.com.store.model.ShoppingCart;

import java.util.List;

/**
 * Интерфейс сервисного слоя для работы с торговой корзиной.
 * Представляет методы с торговой корзиной и торговыми позициями,
 * которые оформляет клиент.
 */
public interface ShoppingCartService {

    /**
     * Возвращает объект корзину.
     * @return Объект класса {@link ShoppingCart} - торговая корзина.
     */
    ShoppingCart getShoppingCart();

    /**
     * Добавляет торговую позицию в список корзины.
     */
    void add(SalePosition salePosition);

    /**
     * Возвращает список всех торговых позиций в корзине.
     */
    List getSalePositions();

    /**
     * Удаляет торговую позицию из корзины.
     */
    void remove(SalePosition salePosition);

    /**
     * Очищает корзину. Удаляет все торговые позиции в корзине.
     */
    void clear();

    /**
     * Возвращает цену корзины - цена всех продаж.
     */
    double getPrice();

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     */
    int getSize();
}
