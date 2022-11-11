package com.qulix.shilomy.trainingtask.web.controller.action.task;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по созданию задачи.
 */
@WebServlet("/createTask")
public class CreateTaskController extends HttpServlet {
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    /**
     * Метод обработки POST запроса, который получает данные из запроса, добавляет новую сущность в базу,
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
        TaskStatus status = TaskStatus.of(request.getParameter(ControllerConstants.STATUS_PARAM_NAME));
        String taskName = request.getParameter(ControllerConstants.TASK_NAME_PARAM);
        String projectId = request.getParameter(ControllerConstants.PROJECT_PARAM_NAME);
        String work = request.getParameter(ControllerConstants.WORK_PARAM_NAME);
        String startYear = request.getParameter(ControllerConstants.START_YEAR_PARAM_NAME);
        String startMonth = request.getParameter(ControllerConstants.START_MONTH_PARAM_NAME);
        String startDay = request.getParameter(ControllerConstants.START_DAY_PARAM_NAME);
        String endYear = request.getParameter(ControllerConstants.END_YEAR_PARAM_NAME);
        String endMonth = request.getParameter(ControllerConstants.END_MONTH_PARAM_NAME);
        String endDay = request.getParameter(ControllerConstants.END_DAY_PARAM_NAME);
        String executorId = request.getParameter(ControllerConstants.EXECUTOR_PARAM_NAME);

        Date startDate = Date.valueOf(startYear + ControllerConstants.MINUS_SIGN + startMonth + ControllerConstants.MINUS_SIGN + startDay);
        Date endDate = Date.valueOf(endYear + ControllerConstants.MINUS_SIGN + endMonth + ControllerConstants.MINUS_SIGN + endDay);

        TaskEntity newTask = new TaskEntity(status, taskName, Long.parseLong(projectId), work, startDate, endDate, Long.parseLong(executorId));

        taskService.add(newTask);
        response.sendRedirect(ControllerConstants.COMMAND_TASK_LIST);
    }
}
