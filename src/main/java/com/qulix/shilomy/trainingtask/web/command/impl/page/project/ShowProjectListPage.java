package com.qulix.shilomy.trainingtask.web.command.impl.page.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.dao.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;

import java.util.List;

public class ShowProjectListPage implements Command {
    private static ShowProjectListPage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String PROJECTS_PAGE = "page.projects";

    private final ProjectService projectService;

    private ShowProjectListPage(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        ProjectDao projectDao = MethodProjectDao.getInstance();
        projectService = ProjectServiceImpl.getInstance(projectDao);
    }

    public static synchronized ShowProjectListPage getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowProjectListPage(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<ProjectEntity> projects = projectService.findAll();
        request.addAttributeToJsp("projects", projects);
        return requestFactory.createForwardResponse(propertyContext.get(PROJECTS_PAGE));
    }
}
