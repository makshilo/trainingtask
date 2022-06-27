package com.qulix.shilomy.trainingtask.web.command.impl.page.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;

public class ShowProjectNameBusyPage implements Command {
    private static ShowProjectNameBusyPage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String CREATE_PROJECT_PAGE = "page.projectNameBusy";

    private ShowProjectNameBusyPage(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    public static synchronized ShowProjectNameBusyPage getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowProjectNameBusyPage(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(CREATE_PROJECT_PAGE));
    }
}
