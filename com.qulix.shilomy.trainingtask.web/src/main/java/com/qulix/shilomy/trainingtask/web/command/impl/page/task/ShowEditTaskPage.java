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

    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String PAGE_MODE = "edit";
    public static final String TASK_PARAM_NAME = "task";
    public static final String ID_PARAM_NAME = "id";

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
        TaskEntity task = taskService.get(Long.parseLong(request.getParameter(ID_PARAM_NAME)));
        request.addAttributeToJsp(PAGE_MODE_PARAM_NAME, PAGE_MODE);
        request.addAttributeToJsp(TASK_PARAM_NAME, task);
        ShowCreateTaskPage.fillPage(request, employeeService, projectService);
        return requestFactory.createForwardResponse(TASK_EDIT_PAGE);
    }
}
