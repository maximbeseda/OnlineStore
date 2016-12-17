package ua.com.store.dao;

import ua.com.store.dao.impl.SalePositionDAOImpl;
import ua.com.store.model.Order;
import ua.com.store.model.SalePosition;
import ua.com.store.model.ShoppingCart;

/**
 * Интерфейс описывает набор методов для работы объектов класса {@link SalePosition} с базой данных.
 * Наследует интерфейс {@link DataDAO}, своих дополнительных методов не имеет.
 *
 * @author Максим Беседа
 * @see DataDAO
 * @see SalePositionDAOImpl
 * @see SalePosition
 * @see Order
 * @see ShoppingCart
 */
public interface SalePositionDAO extends DataDAO<SalePosition> {
}
