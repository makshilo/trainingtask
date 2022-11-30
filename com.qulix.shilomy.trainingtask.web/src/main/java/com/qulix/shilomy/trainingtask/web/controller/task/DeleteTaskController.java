package com.qulix.shilomy.trainingtask.web.controller.task;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет обрабатывающий запросы по удалению проекта
 */
@WebServlet("/deleteTask")
public class DeleteTaskController extends HttpServlet {
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    /**
     * Обработка POST запроса по удалению задачи
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long taskId = Long.parseLong(request.getParameter(ControllerConstant.ID_PARAM.get()));
        taskService.delete(taskId);
        response.sendRedirect(ControllerConstant.TASK_LIST.get());
    }
}
