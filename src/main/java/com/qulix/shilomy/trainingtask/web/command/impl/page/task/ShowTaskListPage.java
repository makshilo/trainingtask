package com.qulix.shilomy.trainingtask.web.command.impl.page.task;

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

public class ShowTaskListPage implements Command {
    private static ShowTaskListPage instance;

    private final RequestFactory requestFactory;

    private static final String TASKS_PAGE = "/jsp/taskList.jsp";

    private final TaskService taskService;

    private final EmployeeService employeeService;

    private final ProjectService projectService;

    private ShowTaskListPage(EmployeeService employeeService, ProjectService projectService, TaskService taskService,
                             RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public static synchronized ShowTaskListPage getInstance(EmployeeService employeeService, ProjectService projectService, TaskService taskService,
                                                            RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowTaskListPage(employeeService, projectService, taskService, requestFactory);
        }
        return instance;
    }

    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), employee.getLastName() + " " + employee.getFirstName());
        }
        return employeeNames;
    }

    private HashMap<Long, String> getProjectNames() {
        final HashMap<Long, String> projectNames = new HashMap<>();
        for (ProjectEntity project : projectService.findAll()) {
            projectNames.put(project.getId(), project.getName());
        }
        return projectNames;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        request.addAttributeToJsp("tasks", taskService.findAll());
        request.addAttributeToJsp("employees", getEmployeeNames());
        request.addAttributeToJsp("projects", getProjectNames());
        return requestFactory.createForwardResponse(TASKS_PAGE);
    }
}
