package com.qulix.shilomy.trainingtask.web.service.impl;

import com.qulix.shilomy.trainingtask.web.dao.DaoFactory;
import com.qulix.shilomy.trainingtask.web.dao.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.Entity;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ServiceFactoryImpl implements ServiceFactory {
    private final Map<Class<?>, EntityService<?>> serviceByEntity = new HashMap<>();
    private static final String SERVICE_NOT_FOUND = "Could not create service for %s class";

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private ServiceFactoryImpl() {

    }

    public static ServiceFactoryImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass) {
        return (EntityService<T>) serviceByEntity
                .computeIfAbsent(modelClass, createServiceForEntity());
    }

    private Function<Class<?>, EntityService<?>> createServiceForEntity() {
        return clazz -> {
            final String className = clazz.getSimpleName();
            switch (className) {
                case "EmployeeEntity":
                    EmployeeDao employeeDao = (EmployeeDao) daoFactory.daoFor(EmployeeEntity.class);
                    return EmployeeServiceImpl.getInstance(employeeDao);
                case "ProjectEntity":
                    ProjectDao projectDao = (ProjectDao) daoFactory.daoFor(ProjectEntity.class);
                    return ProjectServiceImpl.getInstance(projectDao);
                case "TaskEntity":
                    TaskDao taskDao = (TaskDao) daoFactory.daoFor(TaskEntity.class);
                    return TaskServiceImpl.getInstance(taskDao);
                default:
                    throw new IllegalStateException(String.format(SERVICE_NOT_FOUND, className));
            }
        };
    }

    private static class Holder {
        public static ServiceFactoryImpl INSTANCE = new ServiceFactoryImpl();
    }
}
