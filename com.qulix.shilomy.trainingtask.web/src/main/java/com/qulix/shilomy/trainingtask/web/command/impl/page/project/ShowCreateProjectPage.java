package com.qulix.shilomy.trainingtask.web.command.impl.page.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;

public class ShowCreateProjectPage implements Command {
    private static ShowCreateProjectPage instance;

    private final RequestFactory requestFactory;

    private static final String CREATE_PROJECT_PAGE = "/jsp/createProject.jsp";

    private ShowCreateProjectPage(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public static synchronized ShowCreateProjectPage getInstance(RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowCreateProjectPage(requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(CREATE_PROJECT_PAGE);
    }
}
