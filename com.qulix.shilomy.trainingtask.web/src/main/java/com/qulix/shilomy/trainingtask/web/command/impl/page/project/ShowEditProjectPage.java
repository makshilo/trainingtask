package com.qulix.shilomy.trainingtask.web.command.impl.page.project;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

import java.util.HashMap;

public class ShowEditProjectPage implements Command {
    private static ShowEditProjectPage instance;
    private final RequestFactory requestFactory;

    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String PAGE_MODE = "edit";

    public static final String ID_PARAM_NAME = "id";
    public static final String PROJECT_PARAM_NAME = "project";
    public static final String TASKS_PARAM_NAME = "tasks";
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String SPACE_SIGN = " ";

    private static final String PROJECT_EDIT_PAGE = "/jsp/editProject.jsp";

    private final ProjectService projectService;
    private final TaskService taskService;
    private final EmployeeService employeeService;

    private ShowEditProjectPage(ProjectService projectService, TaskService taskService, EmployeeService employeeService,
                                RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public static synchronized ShowEditProjectPage getInstance(ProjectService projectService, TaskService taskService,
    EmployeeService employeeService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowEditProjectPage(projectService, taskService,employeeService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        ProjectEntity project = projectService.get(Long.parseLong(request.getParameter(ID_PARAM_NAME)));
        request.addAttributeToJsp(PAGE_MODE_PARAM_NAME, PAGE_MODE);
        request.addAttributeToJsp(PROJECT_PARAM_NAME, project);
        request.addAttributeToJsp(TASKS_PARAM_NAME, taskService.findByProject(project));
        request.addAttributeToJsp(EMPLOYEES_PARAM_NAME, getEmployeeNames());
        return requestFactory.createForwardResponse((PROJECT_EDIT_PAGE));
    }

    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), employee.getLastName() + SPACE_SIGN + employee.getFirstName());
        }
        return employeeNames;
    }
}
