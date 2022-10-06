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
        ProjectEntity project = projectService.get(Long.parseLong(request.getParameter("id")));
        request.addAttributeToJsp("project", project);
        request.addAttributeToJsp("tasks", taskService.findByProject(project));
        request.addAttributeToJsp("employees", getEmployeeNames());
        return requestFactory.createForwardResponse((PROJECT_EDIT_PAGE));
    }

    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), employee.getLastName() + " " + employee.getFirstName());
        }
        return employeeNames;
    }
}
