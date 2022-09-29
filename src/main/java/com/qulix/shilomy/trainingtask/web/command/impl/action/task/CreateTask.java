package com.qulix.shilomy.trainingtask.web.command.impl.action.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowCreateTaskPage;
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
        TaskStatus status = TaskStatus.of(request.getParameter("status"));
        String taskName = request.getParameter("taskName");
        Long projectId = Long.parseLong(request.getParameter("project"));
        String work = request.getParameter("work");
        Long executorId = Long.parseLong(request.getParameter("executor"));

        try {
            validateFields(request, taskName, work,
                    request.getParameter("startYear"), request.getParameter("startDay"),
                    request.getParameter("endYear"), request.getParameter("endDay"));
        } catch (NullFieldException e) {
            ShowCreateTaskPage.fillPage(request, employeeService, projectService);
            return requestFactory.createForwardResponse(propertyContext.get(CREATE_TASK_PAGE));
        }

        Date startDate = Date.valueOf(request.getParameter("startYear") +
                "-" + request.getParameter("startMonth") +
                "-" + request.getParameter("startDay"));
        Date endDate = Date.valueOf(request.getParameter("endYear") +
                "-" + request.getParameter("endMonth") +
                "-" + request.getParameter("endDay"));

        DateValidator dateValidator = new DateValidatorImpl();

        if (!isDateValid(request, dateValidator)) {
            checkDate(dateValidator, request, "startYear", "startMonth", "startDay",
                    "invalidStartYear", "invalidStartDay", "wrongStartDate");
            checkDate(dateValidator, request,
                    "endYear", "endMonth", "endDay",
                    "invalidEndYear", "invalidEndDay", "wrongEndDate");
            ShowCreateTaskPage.fillPage(request, employeeService, projectService);
            return requestFactory.createForwardResponse(propertyContext.get(CREATE_TASK_PAGE));
        } else if (!endDate.after(startDate)) {
            request.addAttributeToJsp("dateCollision", true);
            ShowCreateTaskPage.fillPage(request, employeeService, projectService);
            return requestFactory.createForwardResponse(propertyContext.get(CREATE_TASK_PAGE));
        } else {
            taskService.add(new TaskEntity(status,taskName, projectId, work, startDate,endDate, executorId));
            return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_TASK_LIST));
        }
    }

    static boolean isDateValid(CommandRequest request, DateValidator dateValidator) {
        return dateValidator.isValid(request.getParameter("startYear"), request.getParameter("startMonth"), request.getParameter("startDay")) == 0 &&
                dateValidator.isValid(request.getParameter("endYear"), request.getParameter("endMonth"), request.getParameter("endDay")) == 0;
    }

    static void checkDate(DateValidator dateValidator, CommandRequest request,
                                  String year, String month, String day,
                                  String invalidYearMsg, String invalidDayMsg, String invalidDateMsg) {
        switch (dateValidator.isValid(request.getParameter(year), request.getParameter(month), request.getParameter(day))) {
            case 1:
                request.addAttributeToJsp(invalidYearMsg, true);
                break;
            case 2:
                request.addAttributeToJsp(invalidDayMsg, true);
                break;
            case 3:
                request.addAttributeToJsp(invalidDayMsg, true);
                request.addAttributeToJsp(invalidYearMsg, true);
                break;
            case 4:
                request.addAttributeToJsp(invalidDateMsg, true);
                break;
        }
    }

    static void validateFields(CommandRequest request, String taskName, String work,
                               String startYear, String startDay, String endYear, String endDay) throws NullFieldException {
        if (taskName == null || taskName.equals("")) {
            request.addAttributeToJsp("taskNameNull", true);
            throw new NullFieldException("Task name is null");
        } else if (work == null || work.equals("")) {
            request.addAttributeToJsp("workNull", true);
            throw new NullFieldException("Work is null");
        } else if (startYear == null || startYear.equals("")) {
            request.addAttributeToJsp("startYearNull", true);
            throw new NullFieldException("Start year is null");
        } else if (startDay == null || startDay.equals("")) {
            request.addAttributeToJsp("startDayNull", true);
            throw new NullFieldException("Start day is null");
        } else if (endYear == null || endYear.equals("")) {
            request.addAttributeToJsp("endYearNull", true);
            throw new NullFieldException("End year is null");
        } else if (endDay == null || endDay.equals("")) {
            request.addAttributeToJsp("endDayNull", true);
            throw new NullFieldException("End day is null");
        }
    }
}
