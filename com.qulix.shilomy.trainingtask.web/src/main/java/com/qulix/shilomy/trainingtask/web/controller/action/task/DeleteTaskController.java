package com.qulix.shilomy.trainingtask.web.controller.action.task;

import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteTask")
public class DeleteTaskController extends HttpServlet {
    public static final String ID_PARAM_NAME = "id";

    private static final String COMMAND_TASK_LIST = "/tasks";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final TaskService taskService = (TaskService) serviceFactory.serviceFor(TaskEntity.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long taskId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        taskService.delete(taskId);
        response.sendRedirect(COMMAND_TASK_LIST);
    }
}
