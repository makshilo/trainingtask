package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.DaoFactory;
import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.entity.Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Класс исполнение фабрики для Дао классов, является синглтоном
 */
public class DaoFactoryImpl implements DaoFactory {
    private static final String DAO_NOT_FOUND = "Could not create dao for %s class";
    private final Map<Class<?>, EntityDao<?>> daoByEntity = new ConcurrentHashMap<>();

    /**
     * Приватный конструктор по умолчанию
     */
    private DaoFactoryImpl() {
    }

    /**
     * Метод получения единственного объекта из класса хранилища
     * @return Объект DaoFactoryImpl
     */
    public static DaoFactoryImpl getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Метод приведения Дао к общему интерфейсу
     * @param modelDao класс необходимой сущности
     * @return Объект Дао приведённый к общему интерфейсу сущности
     * @param <T> класс сущности унаследовынный от базового интерфейса сущности(Entity)
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> EntityDao<T> daoFor(Class<T> modelDao) {
        return (EntityDao<T>) daoByEntity.computeIfAbsent(modelDao, createDaoForEntity());
    }

    /**
     * Метод получения объекта класса Дао для указанного класса
     * @return объект Дао для указанной сущности
     */
    private Function<Class<?>, EntityDao<?>> createDaoForEntity() {
        return clazz -> {
            final String className = clazz.getSimpleName();
            switch (className) {
                case "EmployeeEntity":
                    return MethodEmployeeDao.getInstance();
                case "ProjectEntity":
                    return MethodProjectDao.getInstance();
                case "TaskEntity":
                    return MethodTaskDao.getInstance();
                default:
                    throw new IllegalStateException(String.format(DAO_NOT_FOUND, className));
            }
        };
    }

    /**
     * Класс хранилище для обЪекта DaoFactoryImpl
     */
    private static class Holder {
        private static final DaoFactoryImpl INSTANCE = new DaoFactoryImpl();
    }
}
