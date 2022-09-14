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
import com.qulix.shilomy.trainingtask.web.validator.DateValidator;
import com.qulix.shilomy.trainingtask.web.validator.impl.DateValidatorImpl;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class EditTask implements Command {
    private static EditTask instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_TASK_LIST = "command/tasks_page";

    public static final String EDIT_TASK_PAGE = "page.editTask";

    public static final String DATE_FORMAT = "uuuu-MM-dd";

    private final TaskService taskService;

    private final EmployeeService employeeService;

    private final ProjectService projectService;
    private EditTask(TaskService taskService, EmployeeService employeeService, ProjectService projectService,
                     RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    public static synchronized EditTask getInstance(TaskService taskService, EmployeeService employeeService, ProjectService projectService,
                                                    RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new EditTask(taskService, employeeService, projectService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        String startDate = request.getParameter("startYear") +
                "-" + request.getParameter("startMonth") +
                "-" + request.getParameter("startDay");
        String endDate = request.getParameter("endYear") +
                "-" + request.getParameter("endMonth") +
                "-" + request.getParameter("endDay");
        if (checkDate(startDate)) {
            if (checkDate(endDate)) {
                if (!Date.valueOf(startDate).after(Date.valueOf(endDate))) {
                    taskService.update(fillTask(request, startDate, endDate));
                    return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_TASK_LIST));
                } else {
                    request.addAttributeToJsp("dateCollision", true);
                    request.addAttributeToJsp("task", fillTask(request, startDate, endDate));
                    returnToPage(request);
                    return requestFactory.createForwardResponse(propertyContext.get(EDIT_TASK_PAGE));
                }
            } else {
                request.addAttributeToJsp("wrongEndDate", true);
                request.addAttributeToJsp("task", taskService.get(Long.parseLong(request.getParameter("id"))));
                returnToPage(request);
                return requestFactory.createForwardResponse(propertyContext.get(EDIT_TASK_PAGE));
            }
        } else {
            request.addAttributeToJsp("wrongStartDate", true);
            request.addAttributeToJsp("task", taskService.get(Long.parseLong(request.getParameter("id"))));
            returnToPage(request);
            return requestFactory.createForwardResponse(propertyContext.get(EDIT_TASK_PAGE));
        }
    }

    private boolean checkDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);
        DateValidator dateValidator = new DateValidatorImpl(dateTimeFormatter);
        return dateValidator.isValid(date);
    }

    private static TaskEntity fillTask(CommandRequest request, String startDate, String endDate) {
        return new TaskEntity(
                TaskStatus.of(request.getParameter("stat")),
                request.getParameter("tname"),
                Long.parseLong(request.getParameter("proj")),
                request.getParameter("work"),
                Date.valueOf(startDate),
                Date.valueOf(endDate),
                Long.parseLong(request.getParameter("exec")));
    }

    private void returnToPage(CommandRequest request) {
        request.addAttributeToJsp("employees", employeeService.findAll());
        request.addAttributeToJsp("projects", projectService.findAll());
        if (request.getParameter("projectLock") != null) {
            request.addAttributeToJsp("projectLock", true);
            request.addAttributeToJsp("currentProject", projectService.get(Long.parseLong(request.getParameter("currentProject"))));
        }
    }
}
