package com.qulix.shilomy.trainingtask.web.command.impl.page.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

public class ShowEditTaskPage implements Command {
    private static ShowEditTaskPage instance;

    private final RequestFactory requestFactory;

    private static final String TASK_EDIT_PAGE = "/jsp/editTask.jsp";

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private ShowEditTaskPage(EmployeeService employeeService, TaskService taskService, ProjectService projectService,
                             RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public static synchronized ShowEditTaskPage getInstance(EmployeeService employeeService, TaskService taskService,
                                                            ProjectService projectService,RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowEditTaskPage(employeeService, taskService, projectService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final TaskEntity task = taskService.get(Long.parseLong(request.getParameter("id")));
        request.addAttributeToJsp("task", task);
        request.addAttributeToJsp("currentProject", projectService.get(task.getProjectId()));
        request.addAttributeToJsp("currentExecutor", employeeService.get(task.getExecutorId()));
        fillPage(request, projectService, employeeService);
        return requestFactory.createForwardResponse(TASK_EDIT_PAGE);
    }

    public static void fillPage(CommandRequest request, ProjectService projectService, EmployeeService employeeService) {
        request.addAttributeToJsp("employees", employeeService.findAll());
        request.addAttributeToJsp("projects", projectService.findAll());
        if (request.getParameter("projectLock") != null) {
            request.addAttributeToJsp("projectLock", true);
        }
    }
}
