package com.qulix.shilomy.trainingtask.web.controller.action.task;

import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по удалению задачи.
 */
@WebServlet("/deleteTask")
public class DeleteTaskController extends HttpServlet {
    public static final String ID_PARAM_NAME = "id";

    private static final String COMMAND_TASK_LIST = "/tasks";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final TaskService taskService = (TaskService) serviceFactory.serviceFor(TaskEntity.class);

    /**
     * Метод обработки POST запроса, который получает данные из запроса, удаляет сущность из базы,
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
        Long taskId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        taskService.delete(taskId);
        response.sendRedirect(COMMAND_TASK_LIST);
    }
}
