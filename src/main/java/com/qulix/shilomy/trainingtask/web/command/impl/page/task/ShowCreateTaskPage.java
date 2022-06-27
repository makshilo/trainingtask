package com.qulix.shilomy.trainingtask.web.command.impl.page.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.dao.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodEmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;

import java.util.List;

public class ShowCreateTaskPage implements Command {
    private static ShowCreateTaskPage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    private static final String CREATE_TASK_PAGE = "page.createTask";

    private ShowCreateTaskPage(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        EmployeeDao employeeDao = MethodEmployeeDao.getInstance();
        employeeService = EmployeeServiceImpl.getInstance(employeeDao);
        ProjectDao projectDao = MethodProjectDao.getInstance();
        projectService = ProjectServiceImpl.getInstance(projectDao);
    }

    public static synchronized ShowCreateTaskPage getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowCreateTaskPage(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<EmployeeEntity> employees = employeeService.findAll();
        final List<ProjectEntity> projects = projectService.findAll();
        request.addAttributeToJsp("employees", employees);
        request.addAttributeToJsp("projects", projects);
        return requestFactory.createForwardResponse(propertyContext.get(CREATE_TASK_PAGE));
    }
}
