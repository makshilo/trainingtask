package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

import java.util.List;

public class CreateProject implements Command {
    private static CreateProject instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_PROJECT_LIST = "command/projects_page";

    private final ProjectService projectService;

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
        if(!projectIsFound(projectService.findAll(), request.getParameter("pname"))){
            projectService.add(new ProjectEntity(
                    request.getParameter("pname"),
                    request.getParameter("descr")));
            return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_PROJECT_LIST));
        } else {
            request.addAttributeToJsp("projectNameBusy", true);
            return requestFactory.createForwardResponse(propertyContext.get("page.createProject"));
        }
    }

    private boolean projectIsFound(List<ProjectEntity> projects, String name) {
        for (ProjectEntity project: projects) {
            if(project.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
