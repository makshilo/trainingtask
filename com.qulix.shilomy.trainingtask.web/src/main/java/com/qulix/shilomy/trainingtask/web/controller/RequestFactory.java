package com.qulix.shilomy.trainingtask.web.controller;

import com.qulix.shilomy.trainingtask.web.controller.impl.RequestFactoryImpl;

import javax.servlet.http.HttpServletRequest;

public interface RequestFactory {

    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);

    CommandResponse createRedirectResponse(String path);

    static RequestFactory getInstance() {
        return RequestFactoryImpl.getInstance();
    }
}
