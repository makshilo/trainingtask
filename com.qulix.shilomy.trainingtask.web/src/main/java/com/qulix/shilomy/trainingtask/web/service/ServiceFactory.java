package com.qulix.shilomy.trainingtask.web.service;

import com.qulix.shilomy.trainingtask.web.entity.Entity;
import com.qulix.shilomy.trainingtask.web.service.impl.ServiceFactoryImpl;

/**
 * Интерфейс фабрики сервисов.
 */
public interface ServiceFactory {
    /**
     * Описание метода приведения сервиса к общему интерфейсу.
     * @param modelClass класс необходимой сущности
     * @return объект сервиса, приведённый к общему интерфейсу
     * @param <T> класс сущности унаследовынный от базового интерфейса сущности(Entity)
     */
    <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass);

    /**
     * Метод получения единственного объекта из класса хранилища
     * @return Объект DaoFactoryImpl
     */
    static ServiceFactory getInstance() {
        return ServiceFactoryImpl.getInstance();
    }
}
