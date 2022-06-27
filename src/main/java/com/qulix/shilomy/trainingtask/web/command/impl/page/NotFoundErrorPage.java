package com.qulix.shilomy.trainingtask.web.command.impl.page;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;

public class NotFoundErrorPage implements Command {

    private static final String NOT_FOUND_ERROR_PAGE_PROPERTY = "page.notFoundError";
    private static NotFoundErrorPage instance;

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    private NotFoundErrorPage(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    public static synchronized NotFoundErrorPage getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new NotFoundErrorPage(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(NOT_FOUND_ERROR_PAGE_PROPERTY));
    }
}
