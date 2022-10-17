package com.qulix.shilomy.trainingtask.web.command.impl.page.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;

public class ShowCreateTaskPage implements Command {

    private static ShowCreateTaskPage instance;
    private final RequestFactory requestFactory;

    public static final String CURRENT_PROJECT_PARAM_NAME = "currentProject";
    public static final String PROJECT_LOCK_PARAM = "projectLock";
    public static final String PROJECTS_PARAM_NAME = "projects";
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String TASK_STATUS_PARAM_NAME = "taskStatus";
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String PAGE_MODE = "create";

    private static final String TASK_EDIT_PAGE = "/jsp/editTask.jsp";

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    private ShowCreateTaskPage(EmployeeService employeeService, ProjectService projectService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    public static synchronized ShowCreateTaskPage getInstance(EmployeeService employeeService, ProjectService projectService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowCreateTaskPage(employeeService, projectService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        request.addAttributeToJsp(PAGE_MODE_PARAM_NAME, PAGE_MODE);
        fillPage(request, employeeService, projectService);
        return requestFactory.createForwardResponse(TASK_EDIT_PAGE);
    }

    public static void fillPage(CommandRequest request, EmployeeService employeeService, ProjectService projectService) {
        request.addAttributeToJsp(EMPLOYEES_PARAM_NAME, employeeService.findAll());
        request.addAttributeToJsp(PROJECTS_PARAM_NAME, projectService.findAll());
        request.addAttributeToJsp(TASK_STATUS_PARAM_NAME, TaskStatus.values());
        if (request.getParameter(PROJECT_LOCK_PARAM) != null) {
            ProjectEntity currentProject = projectService.get(Long.parseLong(request.getParameter(CURRENT_PROJECT_PARAM_NAME)));
            request.addAttributeToJsp(PROJECT_LOCK_PARAM, true);
            request.addAttributeToJsp(CURRENT_PROJECT_PARAM_NAME, currentProject);
        }
    }
}
