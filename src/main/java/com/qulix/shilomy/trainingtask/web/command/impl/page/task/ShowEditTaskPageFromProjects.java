package com.qulix.shilomy.trainingtask.web.command.impl.page.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

public class ShowEditTaskPageFromProjects implements Command {
    private static ShowEditTaskPageFromProjects instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String TASK_EDIT_PAGE = "page.editTaskProjectLock";

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private ShowEditTaskPageFromProjects(EmployeeService employeeService, ProjectService projectService, TaskService taskService,
                                         RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    public static synchronized ShowEditTaskPageFromProjects getInstance(EmployeeService employeeService,
                                                                        ProjectService projectService, TaskService taskService,
                                                                        RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowEditTaskPageFromProjects(employeeService, projectService, taskService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final TaskEntity task = taskService.get(Long.parseLong(request.getParameter("id")));
        request.addAttributeToJsp("task", task);
        request.addAttributeToJsp("status", TaskStatus.status);
        request.addAttributeToJsp("currentProject",projectService.get(task.getProjectId()));
        request.addAttributeToJsp("currentExecutor", employeeService.get(task.getExecutorId()));
        request.addAttributeToJsp("employees", employeeService.findAll());
        request.addAttributeToJsp("projects", projectService.findAll());
        return requestFactory.createForwardResponse(propertyContext.get(TASK_EDIT_PAGE));
    }
}
