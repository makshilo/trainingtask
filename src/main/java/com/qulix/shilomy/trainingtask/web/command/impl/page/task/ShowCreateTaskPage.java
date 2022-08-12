package com.qulix.shilomy.trainingtask.web.command.impl.page.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

import java.util.List;

public class ShowCreateTaskPage implements Command {
    private static ShowCreateTaskPage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    private static final String CREATE_TASK_PAGE = "page.createTask";

    private ShowCreateTaskPage(EmployeeService employeeService, ProjectService projectService, RequestFactory requestFactory,
                               PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    public static synchronized ShowCreateTaskPage getInstance(EmployeeService employeeService, ProjectService projectService,
                                                              RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowCreateTaskPage(employeeService, projectService, requestFactory, propertyContext);
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
