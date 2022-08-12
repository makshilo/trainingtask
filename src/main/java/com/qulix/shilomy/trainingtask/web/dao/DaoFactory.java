package com.qulix.shilomy.trainingtask.web.dao;

import com.qulix.shilomy.trainingtask.web.dao.impl.DaoFactoryImpl;
import com.qulix.shilomy.trainingtask.web.entity.Entity;

public interface DaoFactory {
    <T extends Entity> EntityDao<T> daoFor(Class<T> modelDao);

    static DaoFactory getInstance() {
        return DaoFactoryImpl.getInstance();
    }
}
