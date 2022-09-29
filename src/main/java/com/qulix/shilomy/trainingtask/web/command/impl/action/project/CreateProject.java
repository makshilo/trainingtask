package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.exception.NullFieldException;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

import java.util.List;

public class CreateProject implements Command {
    private static CreateProject instance;

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    private static final String COMMAND_PROJECT_LIST = "command/projects_page";
    public static final String CREATE_PROJECT_PAGE = "page.createProject";

    private final ProjectService projectService;

    public static final String PROJECT_NAME_NULL = "projectNameNull";
    public static final String PROJECT_NAME_NULL_MESSAGE = "Project name is null";
    public static final String PROJECT_DESCRIPTION_NULL = "projectDescriptionNull";
    public static final String PROJECT_DESCRIPTION_NULL_MESSAGE = "Project description is null";

    private CreateProject(ProjectService projectService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.projectService = projectService;
    }

    public static synchronized CreateProject getInstance(ProjectService projectService, RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new CreateProject(projectService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        String projectName = request.getParameter("projectName");
        String description = request.getParameter("description");
        try {
            validateFields(request, projectName, description);
        } catch (NullFieldException e) {
            return requestFactory.createForwardResponse(propertyContext.get(CREATE_PROJECT_PAGE));
        }
        ProjectEntity newProject = new ProjectEntity(projectName, description);
        if(!projectIsFound(projectService.findAll(), newProject)){
            projectService.add(newProject);
            return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_PROJECT_LIST));
        } else {
            request.addAttributeToJsp("projectIsFound", true);
            return requestFactory.createForwardResponse(propertyContext.get(CREATE_PROJECT_PAGE));
        }
    }

    private boolean projectIsFound(List<ProjectEntity> projects, ProjectEntity newProject) {
        for (ProjectEntity project: projects) {
            if(project.getName().equals(newProject.getName()) && project.getDescription().equals(newProject.getDescription())) {
                return true;
            }
        }
        return false;
    }

    static void validateFields(CommandRequest request, String projectName, String projectDescription) throws NullFieldException {
        if (projectName == null || projectName.equals("")) {
            request.addAttributeToJsp(PROJECT_NAME_NULL, true);
            throw new NullFieldException(PROJECT_NAME_NULL_MESSAGE);
        } else if (projectDescription == null || projectDescription.equals("")) {
            request.addAttributeToJsp(PROJECT_DESCRIPTION_NULL, true);
            throw new NullFieldException(PROJECT_DESCRIPTION_NULL_MESSAGE);
        }
    }
}
