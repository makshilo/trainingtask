package com.qulix.shilomy.trainingtask.web.command.impl.page;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;

public class NotFoundErrorPage implements Command {

    private static final String NOT_FOUND_ERROR_PAGE_PROPERTY = "/jsp/notFoundError.jsp";
    private static NotFoundErrorPage instance;

    private final RequestFactory requestFactory;

    private NotFoundErrorPage(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public static synchronized NotFoundErrorPage getInstance(RequestFactory requestFactory) {
        if (instance == null) {
            instance = new NotFoundErrorPage(requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(NOT_FOUND_ERROR_PAGE_PROPERTY);
    }
}
