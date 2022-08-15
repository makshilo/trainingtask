package com.qulix.shilomy.trainingtask.web.command.impl.page.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

public class ShowProjectListPage implements Command {
    private static ShowProjectListPage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String PROJECTS_PAGE = "page.projects";

    private final ProjectService projectService;

    private ShowProjectListPage(ProjectService projectService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.projectService = projectService;
    }

    public static synchronized ShowProjectListPage getInstance(ProjectService projectService, RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowProjectListPage(projectService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        request.addAttributeToJsp("projects", projectService.findAll());
        return requestFactory.createForwardResponse(propertyContext.get(PROJECTS_PAGE));
    }
}
