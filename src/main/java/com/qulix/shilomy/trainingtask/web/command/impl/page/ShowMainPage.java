package com.qulix.shilomy.trainingtask.web.command.impl.page;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;

public class ShowMainPage implements Command {
    private static ShowMainPage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String MAIN_PAGE = "page.main";

    private ShowMainPage(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    public static synchronized ShowMainPage getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowMainPage(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(MAIN_PAGE));
    }
}


