package com.qulix.shilomy.trainingtask.web.command.impl.page;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;

public class ShowMainPage implements Command {
    private static ShowMainPage instance;

    private final RequestFactory requestFactory;

    private static final String MAIN_PAGE = "/jsp/main.jsp";

    private ShowMainPage(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public static synchronized ShowMainPage getInstance(RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowMainPage(requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(MAIN_PAGE);
    }
}


