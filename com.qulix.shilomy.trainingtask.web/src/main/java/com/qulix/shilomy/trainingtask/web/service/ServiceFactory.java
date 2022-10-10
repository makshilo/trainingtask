package com.qulix.shilomy.trainingtask.web.service;

import com.qulix.shilomy.trainingtask.web.entity.Entity;
import com.qulix.shilomy.trainingtask.web.service.impl.ServiceFactoryImpl;

public interface ServiceFactory {
    <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass);

    static ServiceFactory getInstance() {
        return ServiceFactoryImpl.getInstance();
    }
}
