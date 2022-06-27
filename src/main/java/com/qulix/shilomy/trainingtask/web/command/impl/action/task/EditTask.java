package com.qulix.shilomy.trainingtask.web.command.impl.action.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.dao.TaskDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodTaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.TaskService;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;

import java.sql.Date;

public class EditTask implements Command {
    private static EditTask instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_TASK_LIST = "command/tasks_page";

    private final TaskService taskService;
    private EditTask(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        TaskDao taskDao = MethodTaskDao.getInstance();
        taskService = TaskServiceImpl.getInstance(taskDao);
    }

    public static synchronized EditTask getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new EditTask(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        taskService.update(new TaskEntity(
                TaskStatus.of(request.getParameter("stat")),
                request.getParameter("tname"),
                Long.parseLong(request.getParameter("proj")),
                request.getParameter("work"),
                Date.valueOf(request.getParameter("start")),
                Date.valueOf(request.getParameter("end")),
                Long.parseLong(request.getParameter("exec")),
                Long.parseLong(request.getParameter("id"))));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_TASK_LIST));
    }
}
