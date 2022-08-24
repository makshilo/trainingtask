package com.qulix.shilomy.trainingtask.web.command.impl.page.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

public class ShowEditTaskPage implements Command {
    private static ShowEditTaskPage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String TASK_EDIT_PAGE = "page.editTask";

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private ShowEditTaskPage(EmployeeService employeeService, TaskService taskService, ProjectService projectService,
                             RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public static synchronized ShowEditTaskPage getInstance(EmployeeService employeeService, TaskService taskService,
                                                            ProjectService projectService,RequestFactory requestFactory,
                                                            PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowEditTaskPage(employeeService, taskService, projectService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final TaskEntity task = taskService.get(Long.parseLong(request.getParameter("id")));
        request.addAttributeToJsp("task", task);
        request.addAttributeToJsp("currentProject",projectService.get(task.getProjectId()));
        request.addAttributeToJsp("currentExecutor", employeeService.get(task.getExecutorId()));
        request.addAttributeToJsp("employees", employeeService.findAll());
        request.addAttributeToJsp("projects", projectService.findAll());
        if (request.getParameter("projectLock") != null) {
            request.addAttributeToJsp("projectLock", true);
        }
        return requestFactory.createForwardResponse(propertyContext.get(TASK_EDIT_PAGE));
    }
}
