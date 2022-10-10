package com.qulix.shilomy.trainingtask.web.command.impl.action.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowCreateTaskPage;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
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

    public static final String TASK_NAME_NULL = "taskNameNull";
    public static final String WORK_NULL = "workNull";
    public static final String START_YEAR_NULL = "startYearNull";
    public static final String START_DAY_NULL = "startDayNull";
    public static final String END_YEAR_NULL = "endYearNull";
    public static final String END_DAY_NULL = "endDayNull";
    public static final String TASK_NAME_NULL_MESSAGE = "Task name is null";
    public static final String WORK_NULL_MESSAGE = "Work is null";
    public static final String WORK_NEGATIVE = "workNegative";
    public static final String WORK_NEGATIVE_MESSAGE = "Work is negative";
    public static final String START_YEAR_NULL_MESSAGE = "Start year is null";
    public static final String START_DAY_NULL_MESSAGE = "Start day is null";
    public static final String END_YEAR_NULL_MESSAGE = "End year is null";
    public static final String END_DAY_NULL_MESSAGE = "End day is null";
    private static CreateTask instance;

    private final RequestFactory requestFactory;

    private static final String COMMAND_TASK_LIST = "/controller?command=tasksPage";
    public static final String CREATE_TASK_PAGE = "/jsp/createTask.jsp";

    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    private CreateTask(TaskService taskService, EmployeeService employeeService, ProjectService projectService,
                       RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    public static synchronized CreateTask getInstance(TaskService taskService, EmployeeService employeeService, ProjectService projectService,
                                                      RequestFactory requestFactory) {
        if (instance == null) {
            instance = new CreateTask(taskService, employeeService, projectService, requestFactory);
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
            return requestFactory.createForwardResponse(CREATE_TASK_PAGE);
        }

        DateValidator dateValidator = new DateValidatorImpl();

        if (isDateValid(request, dateValidator)) {
            Date startDate = Date.valueOf(request.getParameter("startYear") +
                    "-" + request.getParameter("startMonth") +
                    "-" + request.getParameter("startDay"));
            Date endDate = Date.valueOf(request.getParameter("endYear") +
                    "-" + request.getParameter("endMonth") +
                    "-" + request.getParameter("endDay"));
            if (endDate.after(startDate) || endDate.equals(startDate)){
                taskService.add(new TaskEntity(status,taskName, projectId, work, startDate,endDate, executorId));
                return requestFactory.createRedirectResponse(COMMAND_TASK_LIST);
            } else {
                request.addAttributeToJsp("dateCollision", true);
                ShowCreateTaskPage.fillPage(request, employeeService, projectService);
                return requestFactory.createForwardResponse(CREATE_TASK_PAGE);
            }
        } else {
            checkDate(dateValidator, request, "startYear", "startMonth", "startDay",
                    "invalidStartYear", "invalidStartDay", "wrongStartDate");
            checkDate(dateValidator, request,
                    "endYear", "endMonth", "endDay",
                    "invalidEndYear", "invalidEndDay", "wrongEndDate");
            ShowCreateTaskPage.fillPage(request, employeeService, projectService);
            return requestFactory.createForwardResponse(CREATE_TASK_PAGE);
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
            request.addAttributeToJsp(TASK_NAME_NULL, true);
            throw new NullFieldException(TASK_NAME_NULL_MESSAGE);
        } else if (work == null || work.equals("")) {
            request.addAttributeToJsp(WORK_NULL, true);
            throw new NullFieldException(WORK_NULL_MESSAGE);
        } else if (Integer.parseInt(work) < 0) {
            request.addAttributeToJsp(WORK_NEGATIVE, true);
            throw new NullFieldException(WORK_NEGATIVE_MESSAGE);
        } else if (startYear == null || startYear.equals("")) {
            request.addAttributeToJsp(START_YEAR_NULL, true);
            throw new NullFieldException(START_YEAR_NULL_MESSAGE);
        } else if (startDay == null || startDay.equals("")) {
            request.addAttributeToJsp(START_DAY_NULL, true);
            throw new NullFieldException(START_DAY_NULL_MESSAGE);
        } else if (endYear == null || endYear.equals("")) {
            request.addAttributeToJsp(END_YEAR_NULL, true);
            throw new NullFieldException(END_YEAR_NULL_MESSAGE);
        } else if (endDay == null || endDay.equals("")) {
            request.addAttributeToJsp(END_DAY_NULL, true);
            throw new NullFieldException(END_DAY_NULL_MESSAGE);
        }
    }
}
