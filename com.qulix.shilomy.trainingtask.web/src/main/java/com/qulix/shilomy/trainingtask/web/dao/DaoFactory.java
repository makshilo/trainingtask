package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.dao.impl.DaoFactoryImpl;
import com.qulix.shilomy.trainingtask.web.entity.Entity;

/**
 * Интерфейс описывающий реализацию фабрик для Дао класов.
 */
public interface DaoFactory {
    /**
     * Метод приведения Дао к общему интерфейсу
     * @param modelDao класс необходимой сущности
     * @return Объект Дао приведённый к общему интерфейсу сущности
     * @param <T> класс сущности унаследовынный от базового интерфейса сущности(Entity)
     */
    <T extends Entity> EntityDao<T> daoFor(Class<T> modelDao);

    /**
     * Метод получения единственного объекта.
     * @return Объект DaoFactoryImpl
     */
    static DaoFactory getInstance() {
        return DaoFactoryImpl.getInstance();
    }
}
