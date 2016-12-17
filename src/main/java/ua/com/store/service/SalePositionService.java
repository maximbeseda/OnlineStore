package ua.com.store.service;

import ua.com.store.model.SalePosition;
import ua.com.store.service.impl.SalePositionServiceImpl;

/**
 * Интерфейс сервисного слоя, описывает набор методов для работы
 * с объектами класса {@link SalePosition}. Наследует интерфейс {@link MainService}.
 *
 * @author Максим Беседа
 * @see SalePosition
 * @see MainService
 * @see SalePositionServiceImpl
 */
public interface SalePositionService extends MainService<SalePosition> {

}
