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

import java.sql.Date;

public class EditTask implements Command {
    private static EditTask instance;
    private final RequestFactory requestFactory;

    public static final String STATUS_PARAM_NAME = "status";
    public static final String TASK_NAME_PARAM_NAME = "taskName";
    public static final String PROJECT_PARAM_NAME = "project";
    public static final String WORK_PARAM_NAME = "work";
    public static final String START_YEAR_PARAM_NAME = "startYear";
    public static final String START_MONTH_PARAM_NAME = "startMonth";
    public static final String START_DAY_PARAM_NAME = "startDay";
    public static final String END_YEAR_PARAM_NAME = "endYear";
    public static final String END_MONTH_PARAM_NAME = "endMonth";
    public static final String END_DAY_PARAM_NAME = "endDay";
    public static final String EXECUTOR_PARAM_NAME = "executor";
    public static final String ID_PARAM_NAME = "id";
    public static final String DATE_COLLISION = "dateCollision";
    public static final String MINUS_SIGN = "-";

    private static final String COMMAND_TASK_LIST = "/controller?command=tasksPage";

    public static final String EDIT_TASK_PAGE = "/jsp/editTask.jsp";

    private final TaskService taskService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private EditTask(TaskService taskService, EmployeeService employeeService, ProjectService projectService,
                     RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.taskService = taskService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    public static synchronized EditTask getInstance(TaskService taskService, EmployeeService employeeService, ProjectService projectService,
                                                    RequestFactory requestFactory) {
        if (instance == null) {
            instance = new EditTask(taskService, employeeService, projectService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        TaskStatus status = TaskStatus.of(request.getParameter(STATUS_PARAM_NAME));
        String taskName = request.getParameter(TASK_NAME_PARAM_NAME);
        String projectId = request.getParameter(PROJECT_PARAM_NAME);
        String work = request.getParameter(WORK_PARAM_NAME);
        String startYear = request.getParameter(START_YEAR_PARAM_NAME);
        String startMonth = request.getParameter(START_MONTH_PARAM_NAME);
        String startDay = request.getParameter(START_DAY_PARAM_NAME);
        String endYear = request.getParameter(END_YEAR_PARAM_NAME);
        String endMonth = request.getParameter(END_MONTH_PARAM_NAME);
        String endDay = request.getParameter(END_DAY_PARAM_NAME);
        String executorId = request.getParameter(EXECUTOR_PARAM_NAME);
        Long id = Long.parseLong(request.getParameter(ID_PARAM_NAME));

        if (CreateTask.validateFields(request, taskName, projectId, work,
                startYear, startMonth, startDay,
                endYear, endMonth, endDay,
                executorId, status))
        {
            Date startDate = Date.valueOf(startYear + MINUS_SIGN + startMonth + MINUS_SIGN + startDay);
            Date endDate = Date.valueOf(endYear + MINUS_SIGN + endMonth + MINUS_SIGN + endDay);
            Long actualProjectId = Long.parseLong(projectId);
            Long actualExecutorId = Long.parseLong(executorId);
            if (endDate.after(startDate) || endDate.equals(startDate)){
                taskService.update(new TaskEntity(status,taskName, actualProjectId, work, startDate,endDate, actualExecutorId, id));
                return requestFactory.createRedirectResponse(COMMAND_TASK_LIST);
            } else {
                request.addAttributeToJsp(DATE_COLLISION, true);
                ShowCreateTaskPage.fillPage(request, employeeService, projectService);
                return requestFactory.createForwardResponse(EDIT_TASK_PAGE);
            }
        } else {
            ShowCreateTaskPage.fillPage(request, employeeService, projectService);
            return requestFactory.createForwardResponse(EDIT_TASK_PAGE);
        }
    }
}
