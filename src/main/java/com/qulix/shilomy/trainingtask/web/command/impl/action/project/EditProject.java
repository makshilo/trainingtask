package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.exception.NullFieldException;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

public class EditProject implements Command {

    private static EditProject instance;

    private final RequestFactory requestFactory;

    private static final String COMMAND_PROJECTS_LIST = "/controller?command=projectsPage";

    public static final String EDIT_PROJECT_PAGE = "/jsp/editProject.jsp";

    private final ProjectService projectService;

    private EditProject(ProjectService projectService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.projectService = projectService;
    }

    public static synchronized EditProject getInstance(ProjectService projectService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new EditProject(projectService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            CreateProject.validateFields(request, request.getParameter("projectName"), request.getParameter("description"));
        } catch (NullFieldException e) {
            return requestFactory.createForwardResponse(EDIT_PROJECT_PAGE);
        }
        projectService.update(new ProjectEntity(
                request.getParameter("projectName"),
                request.getParameter("description"),
                Long.parseLong(request.getParameter("id"))));
        return requestFactory.createRedirectResponse(COMMAND_PROJECTS_LIST);
    }
}
