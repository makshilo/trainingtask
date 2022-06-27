package com.qulix.shilomy.trainingtask.web.controller;

import com.qulix.shilomy.trainingtask.web.controller.impl.PropertyContextImpl;

public interface PropertyContext {

    String get(String name);

    static PropertyContext getInstance() {
        return PropertyContextImpl.getInstance();
    }

}
