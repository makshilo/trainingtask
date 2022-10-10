package com.qulix.shilomy.trainingtask.web.command.impl.page.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

public class ShowProjectListPage implements Command {
    private static ShowProjectListPage instance;

    private final RequestFactory requestFactory;

    private static final String PROJECTS_PAGE = "/jsp/projectList.jsp";

    private final ProjectService projectService;

    private ShowProjectListPage(ProjectService projectService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.projectService = projectService;
    }

    public static synchronized ShowProjectListPage getInstance(ProjectService projectService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowProjectListPage(projectService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        request.addAttributeToJsp("projects", projectService.findAll());
        return requestFactory.createForwardResponse(PROJECTS_PAGE);
    }
}
