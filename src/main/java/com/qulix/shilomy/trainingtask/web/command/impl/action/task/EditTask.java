package com.qulix.shilomy.trainingtask.web.command.impl.action.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowCreateTaskPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowEditTaskPage;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.exception.NullFieldException;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.TaskService;
import com.qulix.shilomy.trainingtask.web.validator.DateValidator;
import com.qulix.shilomy.trainingtask.web.validator.impl.DateValidatorImpl;

import java.sql.Date;

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
        TaskStatus status = TaskStatus.of(request.getParameter("status"));
        String taskName = request.getParameter("taskName");
        Long projectId = Long.parseLong(request.getParameter("project"));
        String work = request.getParameter("work");
        Long executorId = Long.parseLong(request.getParameter("executor"));
        Long id = Long.parseLong(request.getParameter("id"));

        try {
            CreateTask.validateFields(request, taskName, work,
                    request.getParameter("startYear"), request.getParameter("startDay"),
                    request.getParameter("endYear"), request.getParameter("endDay"));
        } catch (NullFieldException e) {
            ShowCreateTaskPage.fillPage(request, employeeService, projectService);
            return requestFactory.createForwardResponse(propertyContext.get(EDIT_TASK_PAGE));
        }

        DateValidator dateValidator = new DateValidatorImpl();

        if (CreateTask.isDateValid(request, dateValidator)) {
            Date startDate = Date.valueOf(request.getParameter("startYear") +
                    "-" + request.getParameter("startMonth") +
                    "-" + request.getParameter("startDay"));
            Date endDate = Date.valueOf(request.getParameter("endYear") +
                    "-" + request.getParameter("endMonth") +
                    "-" + request.getParameter("endDay"));
            if (endDate.after(startDate)){
                taskService.update(new TaskEntity(status, taskName, projectId, work, startDate, endDate, executorId, id));
                return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_TASK_LIST));
            } else {
                request.addAttributeToJsp("dateCollision", true);
                ShowEditTaskPage.fillPage(request, projectService, employeeService);
                return requestFactory.createForwardResponse(propertyContext.get(EDIT_TASK_PAGE));
            }
        } else {
            CreateTask.checkDate(dateValidator, request, "startYear", "startMonth", "startDay",
                    "invalidStartYear", "invalidStartDay", "wrongStartDate");
            CreateTask.checkDate(dateValidator, request,
                    "endYear", "endMonth", "endDay",
                    "invalidEndYear", "invalidEndDay", "wrongEndDate");
            ShowEditTaskPage.fillPage(request, projectService, employeeService);
            return requestFactory.createForwardResponse(propertyContext.get(EDIT_TASK_PAGE));
        }
    }
}
