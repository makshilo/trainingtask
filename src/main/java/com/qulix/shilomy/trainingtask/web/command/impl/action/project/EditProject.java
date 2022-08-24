package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

import java.util.List;

public class EditProject implements Command {

    private static EditProject instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_PROJECTS_LIST = "command/projects_page";

    private static final String COMMAND_PROJECT_EDIT_PAGE = "command/edit_project_page";

    private static final String ID_PARAM = "&id=";
    private static final String NAME_BUSY_PARAM = "&projectNameBusy";

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
        if (!projectIsFound(projectService.findAll(), request.getParameter("pname"))) {
            projectService.update(new ProjectEntity(
                    request.getParameter("pname"),
                    request.getParameter("descr"),
                    Long.parseLong(request.getParameter("id"))));
            return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_PROJECTS_LIST));
        } else {

            return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_PROJECT_EDIT_PAGE)
                    + ID_PARAM + request.getParameter("id")
                    + NAME_BUSY_PARAM);
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
