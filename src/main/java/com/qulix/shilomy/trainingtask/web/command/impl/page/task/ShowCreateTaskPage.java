package com.qulix.shilomy.trainingtask.web.command.impl.page.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

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
        request.addAttributeToJsp("employees", employeeService.findAll());
        request.addAttributeToJsp("projects", projectService.findAll());
        if (request.getParameter("projectLock") != null) {
            request.addAttributeToJsp("projectLock", true);
            request.addAttributeToJsp("currentProject", projectService.get(Long.parseLong(request.getParameter("currentProject"))));
        }
        return requestFactory.createForwardResponse(propertyContext.get(CREATE_TASK_PAGE));
    }
}
