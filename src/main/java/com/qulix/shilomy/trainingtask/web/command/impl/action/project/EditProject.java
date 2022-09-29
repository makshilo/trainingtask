package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.exception.NullFieldException;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

public class EditProject implements Command {

    private static EditProject instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_PROJECTS_LIST = "command/projects_page";

    public static final String EDIT_PROJECT_PAGE = "page.editProject";

    private final ProjectService projectService;

    private EditProject(ProjectService projectService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.projectService = projectService;
    }

    public static synchronized EditProject getInstance(ProjectService projectService, RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new EditProject(projectService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            CreateProject.validateFields(request, request.getParameter("projectName"), request.getParameter("description"));
        } catch (NullFieldException e) {
            return requestFactory.createForwardResponse(propertyContext.get(EDIT_PROJECT_PAGE));
        }
        projectService.update(new ProjectEntity(
                request.getParameter("projectName"),
                request.getParameter("description"),
                Long.parseLong(request.getParameter("id"))));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_PROJECTS_LIST));
    }
}
