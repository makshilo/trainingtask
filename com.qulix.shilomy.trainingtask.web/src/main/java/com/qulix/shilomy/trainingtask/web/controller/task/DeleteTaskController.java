package com.qulix.shilomy.trainingtask.web.controller.task;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.qulix.shilomy.trainingtask.data.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.TaskServiceImpl;

/**
 * Сервлет обрабатывающий запросы по удалению проекта
 */
@WebServlet("/deleteTask")
public class DeleteTaskController extends HttpServlet {
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    /**
     * Обработка POST запроса по удалению задачи
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long taskId = Long.parseLong(request.getParameter(TaskParam.ID.get()));
        taskService.delete(taskId);
        response.sendRedirect(TaskParam.TASK_LIST.get());
    }
}
