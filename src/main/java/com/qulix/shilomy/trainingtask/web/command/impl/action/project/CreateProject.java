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

public class CreateProject implements Command {
    private static CreateProject instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_PROJECT_LIST = "command/projects_page";

    private static final String COMMAND_NAME_BUSY = "command/project_name_is_busy";

    private final ProjectService projectService;

    private final ProjectDao projectDao;

    private CreateProject(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        projectDao = MethodProjectDao.getInstance();
        projectService = ProjectServiceImpl.getInstance(projectDao);
    }

    public static synchronized CreateProject getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new CreateProject(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if(projectDao.receiveProjectByName(request.getParameter("pname")).isEmpty()){
            projectService.add(new ProjectEntity(
                    request.getParameter("pname"),
                    request.getParameter("descr")));
            return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_PROJECT_LIST));
        } else {
            return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_NAME_BUSY));
        }
    }
}
