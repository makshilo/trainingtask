package com.qulix.shilomy.trainingtask.web.command.impl.action.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowEditTaskPage;
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

import static com.qulix.shilomy.trainingtask.web.command.impl.action.task.CreateTask.checkDate;
import static com.qulix.shilomy.trainingtask.web.command.impl.action.task.CreateTask.isDateValid;

public class EditTask implements Command {
    private static EditTask instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_TASK_LIST = "command/tasks_page";

    public static final String EDIT_TASK_PAGE = "page.editTask";

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

        DateValidator dateValidator = new DateValidatorImpl();

        if (!isDateValid(request, dateValidator)) {
            checkDate(dateValidator, request, "startYear", "startMonth", "startDay",
                    "invalidStartYear", "invalidStartDay", "wrongStartDate");
            checkDate(dateValidator, request,
                    "endYear", "endMonth", "endDay",
                    "invalidEndYear", "invalidEndDay", "wrongEndDate");
            ShowEditTaskPage.fillPage(request, projectService, employeeService);
            return requestFactory.createForwardResponse(propertyContext.get(EDIT_TASK_PAGE));
        } else if (!Date.valueOf(endDate).after(Date.valueOf(startDate))) {
            request.addAttributeToJsp("dateCollision", true);
            ShowEditTaskPage.fillPage(request, projectService, employeeService);
            return requestFactory.createForwardResponse(propertyContext.get(EDIT_TASK_PAGE));
        } else {
            taskService.update(new TaskEntity(
                    TaskStatus.of(request.getParameter("stat")),
                    request.getParameter("tname"),
                    Long.parseLong(request.getParameter("proj")),
                    request.getParameter("work"),
                    Date.valueOf(startDate),
                    Date.valueOf(endDate),
                    Long.parseLong(request.getParameter("exec")),
                    Long.parseLong(request.getParameter("id"))));
            return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_TASK_LIST));
        }
    }
}
