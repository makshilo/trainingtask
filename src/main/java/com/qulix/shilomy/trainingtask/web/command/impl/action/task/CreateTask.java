package com.qulix.shilomy.trainingtask.web.command.impl.action.task;

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

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateTask implements Command {
    private static CreateTask instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_TASK_LIST = "command/tasks_page";

    public static final String CREATE_TASK_PAGE = "page.createTask";

    private final TaskService taskService;

    private final EmployeeService employeeService;

    private final ProjectService projectService;

    private CreateTask(TaskService taskService, EmployeeService employeeService, ProjectService projectService,
                       RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    public static synchronized CreateTask getInstance(TaskService taskService, EmployeeService employeeService, ProjectService projectService,
                                                      RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new CreateTask(taskService, employeeService, projectService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        Date startDate = new Date(prepareDate(
                request.getParameter("startYear"),
                request.getParameter("startMonth"),
                request.getParameter("startDay")));
        Date endDate = new Date(prepareDate(
                request.getParameter("endYear"),
                request.getParameter("endMonth"),
                request.getParameter("endDay")));
        TaskEntity filledTask = new TaskEntity(
                TaskStatus.of(request.getParameter("stat")),
                request.getParameter("tname"),
                Long.parseLong(request.getParameter("proj")),
                request.getParameter("work"),
                startDate,
                endDate,
                Long.parseLong(request.getParameter("exec")));
        if (startDate.after(endDate)) {
            request.addAttributeToJsp("dateCollision", true);
            request.addAttributeToJsp("filledTask", filledTask);
            request.addAttributeToJsp("employees", employeeService.findAll());
            request.addAttributeToJsp("projects", projectService.findAll());
            if (request.getParameter("projectLock") != null) {
                request.addAttributeToJsp("projectLock", true);
                request.addAttributeToJsp("currentProject", projectService.get(Long.parseLong(request.getParameter("currentProject"))));
            }
            return requestFactory.createForwardResponse(propertyContext.get(CREATE_TASK_PAGE));
        } else {
            taskService.add(filledTask);
            return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_TASK_LIST));
        }
    }


    private long prepareDate(String year, String month, String day) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormatter.parse(year + "-" + month + "-" + day).getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
