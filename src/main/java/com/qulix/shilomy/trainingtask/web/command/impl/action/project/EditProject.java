package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.dao.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;

public class EditProject implements Command {

    private static EditProject instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_PROJECTS_LIST = "command/projects_page";

    private final ProjectService projectService;
    private EditProject(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        ProjectDao projectDao = MethodProjectDao.getInstance();
        projectService = ProjectServiceImpl.getInstance(projectDao);
    }

    public static synchronized EditProject getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new EditProject(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        projectService.update(new ProjectEntity(
                request.getParameter("pname"),
                request.getParameter("descr"),
                Long.parseLong(request.getParameter("id"))));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_PROJECTS_LIST));
    }
}
