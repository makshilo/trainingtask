package com.qulix.shilomy.trainingtask.web.command.impl.action.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.dao.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodProjectDao;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;

public class DeleteProject implements Command {
    private static DeleteProject instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_PROJECT_LIST = "command/projects_page";

    private final ProjectService projectService;
    private DeleteProject(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        ProjectDao projectDao = MethodProjectDao.getInstance();
        projectService = ProjectServiceImpl.getInstance(projectDao);
    }

    public static synchronized DeleteProject getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new DeleteProject(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        projectService.delete(Long.parseLong(request.getParameter("id")));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_PROJECT_LIST));
    }
}
