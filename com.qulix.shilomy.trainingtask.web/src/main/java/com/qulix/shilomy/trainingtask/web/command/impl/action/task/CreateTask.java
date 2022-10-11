package com.qulix.shilomy.trainingtask.web.command.impl.action.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowCreateTaskPage;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
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

public class CreateTask implements Command {
    private static CreateTask instance;
    private final RequestFactory requestFactory;

    public static final String STATUS_PARAM_NAME = "status";
    public static final String TASK_NAME_PARAM = "taskName";
    public static final String PROJECT_PARAM_NAME = "project";
    public static final String WORK_PARAM_NAME = "work";
    public static final String START_YEAR_PARAM_NAME = "startYear";
    public static final String START_MONTH_PARAM_NAME = "startMonth";
    public static final String START_DAY_PARAM_NAME = "startDay";
    public static final String END_YEAR_PARAM_NAME = "endYear";
    public static final String END_MONTH_PARAM_NAME = "endMonth";
    public static final String END_DAY_PARAM_NAME = "endDay";
    public static final String EXECUTOR_PARAM_NAME = "executor";
    public static final String EMPTY_STRING = "";
    public static final String MINUS_SIGN = "-";


    public static final String DATE_FORMAT = "uuuu-MM-dd";
    public static final String YEAR_FORMAT = "uuuu";
    public static final String DAY_FORMAT = "dd";

    public static final String TASK_NAME_NULL = "taskNameNull";
    public static final String PROJECT_NULL = "projectNull";
    public static final String WORK_NULL = "workNull";
    public static final String WORK_NEGATIVE = "workNegative";
    public static final String START_YEAR_NULL = "startYearNull";
    public static final String INVALID_START_YEAR = "invalidStartYear";
    public static final String START_MONTH_NULL = "startMonthNull";
    public static final String START_DAY_NULL = "startDayNull";
    public static final String INVALID_START_DAY = "invalidStartDay";
    public static final String WRONG_START_DATE = "wrongStartDate";
    public static final String END_YEAR_NULL = "endYearNull";
    public static final String INVALID_END_YEAR = "invalidEndYear";
    public static final String END_MONTH_NULL = "endMonthNull";
    public static final String END_DAY_NULL = "endDayNull";
    public static final String INVALID_END_DAY = "invalidEndDay";
    public static final String WRONG_END_DATE = "wrongEndDate";
    public static final String EXECUTOR_NULL = "executorNull";
    public static final String STATUS_NULL = "statusNull";
    public static final String DATE_COLLISION = "dateCollision";

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

    public static synchronized CreateTask getInstance(TaskService taskService, EmployeeService employeeService,
                                                      ProjectService projectService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new CreateTask(taskService, employeeService, projectService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        TaskStatus status = TaskStatus.of(request.getParameter(STATUS_PARAM_NAME));
        String taskName = request.getParameter(TASK_NAME_PARAM);
        String projectId = request.getParameter(PROJECT_PARAM_NAME);
        String work = request.getParameter(WORK_PARAM_NAME);
        String startYear = request.getParameter(START_YEAR_PARAM_NAME);
        String startMonth = request.getParameter(START_MONTH_PARAM_NAME);
        String startDay = request.getParameter(START_DAY_PARAM_NAME);
        String endYear = request.getParameter(END_YEAR_PARAM_NAME);
        String endMonth = request.getParameter(END_MONTH_PARAM_NAME);
        String endDay = request.getParameter(END_DAY_PARAM_NAME);
        String executorId = request.getParameter(EXECUTOR_PARAM_NAME);

        if (validateFields(request, taskName, projectId, work,
                startYear, startMonth, startDay,
                endYear, endMonth, endDay,
                executorId, status))
        {
            Date startDate = Date.valueOf(startYear + MINUS_SIGN + startMonth + MINUS_SIGN + startDay);
            Date endDate = Date.valueOf(endYear + MINUS_SIGN + endMonth + MINUS_SIGN + endDay);
            Long actualProjectId = Long.parseLong(projectId);
            Long actualExecutorId = Long.parseLong(executorId);
            if (endDate.after(startDate) || endDate.equals(startDate)){
                taskService.add(new TaskEntity(status,taskName, actualProjectId, work, startDate,endDate, actualExecutorId));
                return requestFactory.createRedirectResponse(COMMAND_TASK_LIST);
            } else {
                request.addAttributeToJsp(DATE_COLLISION, true);
                ShowCreateTaskPage.fillPage(request, employeeService, projectService);
                return requestFactory.createForwardResponse(CREATE_TASK_PAGE);
            }
        } else {
            ShowCreateTaskPage.fillPage(request, employeeService, projectService);
            return requestFactory.createForwardResponse(CREATE_TASK_PAGE);
        }
    }

    static boolean validateFields(CommandRequest request, String taskName, String projectId, String work,
                               String startYear, String startMonth, String startDay,
                                  String endYear, String endMonth, String endDay, String executorId, TaskStatus status) {

        DateValidator dateValidator = DateValidatorImpl.getInstance();

        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern(YEAR_FORMAT).withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern(DAY_FORMAT).withResolverStyle(ResolverStyle.STRICT);

        if (taskName == null || taskName.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(TASK_NAME_NULL, true);
            return false;
        }
        if (projectId == null || projectId.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(PROJECT_NULL, true);
            return false;
        }
        if (work == null || work.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(WORK_NULL, true);
            return false;
        } else if (Integer.parseInt(work) < 0) {
            request.addAttributeToJsp(WORK_NEGATIVE, true);
            return false;
        }
        if (startYear == null || startYear.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(START_YEAR_NULL, true);
            return false;
        } else if (!dateValidator.isValid(yearFormatter, startYear)) {
            request.addAttributeToJsp(INVALID_START_YEAR, true);
            return false;
        }
        if (startMonth == null || startMonth.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(START_MONTH_NULL, true);
            return false;
        }
        if (startDay == null || startDay.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(START_DAY_NULL, true);
            return false;
        } else if (!dateValidator.isValid(dayFormatter, startDay)) {
            request.addAttributeToJsp(INVALID_START_DAY, true);
            return false;
        }
        if (!isDateValid(dateValidator, startYear, startMonth, startDay)) {
            request.addAttributeToJsp(WRONG_START_DATE, true);
            return false;
        }
        if (endYear == null || endYear.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(END_YEAR_NULL, true);
            return false;
        } else if (!dateValidator.isValid(yearFormatter, endYear)) {
            request.addAttributeToJsp(INVALID_END_YEAR, true);
            return false;
        }
        if (endMonth == null || endMonth.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(END_MONTH_NULL, true);
            return false;
        }
        if (endDay == null || endDay.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(END_DAY_NULL, true);
            return false;
        } else if (!dateValidator.isValid(dayFormatter, endDay)) {
            request.addAttributeToJsp(INVALID_END_DAY, true);
            return false;
        }
        if (!isDateValid(dateValidator, endYear, endMonth, endDay)) {
            request.addAttributeToJsp(WRONG_END_DATE, true);
            return false;
        }
        if (executorId == null || executorId.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(EXECUTOR_NULL, true);
            return false;
        }
        if (status == null) {
            request.addAttributeToJsp(STATUS_NULL, true);
            return false;
        }
        return true;
    }

    static boolean isDateValid(DateValidator dateValidator, String year, String month, String day) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);

        return dateValidator.isValid(dateFormatter, year + MINUS_SIGN + month + MINUS_SIGN + day);
    }
}
