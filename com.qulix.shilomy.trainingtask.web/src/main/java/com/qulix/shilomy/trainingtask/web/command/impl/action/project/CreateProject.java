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

    private static final String COMMAND_PROJECT_LIST = "/controller?command=projectsPage";
    public static final String CREATE_PROJECT_PAGE = "/jsp/createProject.jsp";

    private final ProjectService projectService;

    public static final String PROJECT_NAME_NULL = "projectNameNull";
    public static final String PROJECT_DESCRIPTION_NULL = "projectDescriptionNull";

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
        String projectName = request.getParameter("projectName");
        String description = request.getParameter("description");
        if (validateFields(request, projectName, description)) {
            ProjectEntity newProject = new ProjectEntity(projectName, description);
            if(!projectIsFound(projectService.findAll(), newProject)){
                projectService.add(newProject);
                return requestFactory.createRedirectResponse(COMMAND_PROJECT_LIST);
            } else {
                request.addAttributeToJsp("projectIsFound", true);
                return requestFactory.createForwardResponse(CREATE_PROJECT_PAGE);
            }
        } else {
            return requestFactory.createForwardResponse(CREATE_PROJECT_PAGE);
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
        if (projectName == null || projectName.equals("")) {
            request.addAttributeToJsp(PROJECT_NAME_NULL, true);
            return false;
        }
        if (projectDescription == null || projectDescription.equals("")) {
            request.addAttributeToJsp(PROJECT_DESCRIPTION_NULL, true);
            return false;
        }
        return true;
    }
}
