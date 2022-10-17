package com.qulix.shilomy.trainingtask.web.command.impl.page.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;

public class ShowCreateProjectPage implements Command {
    private static ShowCreateProjectPage instance;
    private final RequestFactory requestFactory;

    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String PAGE_MODE = "create";

    private static final String PROJECT_EDIT_PAGE = "/jsp/editProject.jsp";

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
        request.addAttributeToJsp(PAGE_MODE_PARAM_NAME, PAGE_MODE);
        return requestFactory.createForwardResponse(PROJECT_EDIT_PAGE);
    }
}
