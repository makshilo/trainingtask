package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

public class EditProject implements Command {

    private static EditProject instance;
    private final RequestFactory requestFactory;

    public static final String PROJECT_NAME_PARAM = "projectName";
    public static final String DESCRIPTION_PARAM_NAME = "description";
    public static final String ID_PARAM_NAME = "id";

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
        String projectName = request.getParameter(PROJECT_NAME_PARAM);
        String description = request.getParameter(DESCRIPTION_PARAM_NAME);
        Long projectId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        if (CreateProject.validateFields(request, projectName, description)) {
            projectService.update(new ProjectEntity(projectName, description, projectId));
            return requestFactory.createRedirectResponse(COMMAND_PROJECTS_LIST);
        } else {
            return requestFactory.createForwardResponse(EDIT_PROJECT_PAGE);
        }
    }
}
