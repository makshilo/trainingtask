package com.qulix.shilomy.trainingtask.web.controller.action.task;

import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по редактированию задачи.
 */
@WebServlet("/editTask")
public class EditTaskController extends HttpServlet {
    public static final String ID_PARAM_NAME = "id";
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
    public static final String MINUS_SIGN = "-";

    private static final String COMMAND_TASK_LIST = "/tasks";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final TaskService taskService = (TaskService) serviceFactory.serviceFor(TaskEntity.class);

    /**
     * Метод обработки POST запроса, который получает данные из запроса, обновляет сущность в базе,
     * а потом перенаправляет на страницу со списком задач.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                  полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws IOException возникает в случае проблем с получением строки для перенаправления
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        Long id = Long.parseLong(request.getParameter(ID_PARAM_NAME));

        Date startDate = Date.valueOf(startYear + MINUS_SIGN + startMonth + MINUS_SIGN + startDay);
        Date endDate = Date.valueOf(endYear + MINUS_SIGN + endMonth + MINUS_SIGN + endDay);

        TaskEntity newTask = new TaskEntity(status, taskName, Long.parseLong(projectId), work, startDate, endDate, Long.parseLong(executorId), id);
        taskService.update(newTask);
        response.sendRedirect(COMMAND_TASK_LIST);
    }
}
