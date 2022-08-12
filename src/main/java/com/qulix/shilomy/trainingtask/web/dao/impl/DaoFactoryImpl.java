package com.qulix.shilomy.trainingtask.web.dao.impl;

import com.qulix.shilomy.trainingtask.web.dao.DaoFactory;
import com.qulix.shilomy.trainingtask.web.dao.EntityDao;
import com.qulix.shilomy.trainingtask.web.entity.Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DaoFactoryImpl implements DaoFactory {
    private static final String DAO_NOT_FOUND = "Could not create dao for %s class";
    private final Map<Class<?>, EntityDao<?>> daoByEntity = new ConcurrentHashMap<>();

    private DaoFactoryImpl() {
    }
    public static DaoFactoryImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> EntityDao<T> daoFor(Class<T> modelDao) {
        return (EntityDao<T>) daoByEntity.computeIfAbsent(modelDao, createDaoForEntity());
    }

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

    private static class Holder {
        private static final DaoFactoryImpl INSTANCE = new DaoFactoryImpl();
    }
}
