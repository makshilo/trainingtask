package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

import java.util.List;

public class CreateProject implements Command {
    private static CreateProject instance;
    private final RequestFactory requestFactory;

    public static final String PROJECT_NAME_PARAM = "projectName";
    public static final String DESCRIPTION_PARAM_NAME = "description";

    public static final String PROJECT_IS_FOUND = "projectIsFound";
    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
    public static final String PROJECT_NAME_NULL = "projectNameNull";
    public static final String PROJECT_DESCRIPTION_NULL = "projectDescriptionNull";
    public static final String EMPTY_STRING = "";

    private static final String COMMAND_PROJECT_LIST = "/controller?command=projectsPage";
    public static final String EDIT_PROJECT_PAGE = "/jsp/editProject.jsp";

    private final ProjectService projectService;

    private CreateProject(ProjectService projectService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.projectService = projectService;
    }

    public static synchronized CreateProject getInstance(ProjectService projectService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new CreateProject(projectService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        String projectName = request.getParameter(PROJECT_NAME_PARAM);
        String description = request.getParameter(DESCRIPTION_PARAM_NAME);
        if (validateFields(request, projectName, description)) {
            ProjectEntity newProject = new ProjectEntity(projectName, description);
            if(!projectIsFound(projectService.findAll(), newProject)){
                projectService.add(newProject);
                return requestFactory.createRedirectResponse(COMMAND_PROJECT_LIST);
            } else {
                request.addAttributeToJsp(VALIDATION_ERROR_PARAM_NAME, PROJECT_IS_FOUND);
                return requestFactory.createForwardResponse(EDIT_PROJECT_PAGE);
            }
        } else {
            return requestFactory.createForwardResponse(EDIT_PROJECT_PAGE);
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

    static boolean validateFields(CommandRequest request, String projectName, String projectDescription) {
        if (projectName == null || projectName.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(VALIDATION_ERROR_PARAM_NAME, PROJECT_NAME_NULL);
            return false;
        }
        if (projectDescription == null || projectDescription.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(VALIDATION_ERROR_PARAM_NAME, PROJECT_DESCRIPTION_NULL);
            return false;
        }
        return true;
    }
}
