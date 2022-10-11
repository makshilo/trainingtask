package com.qulix.shilomy.trainingtask.web.command.impl.action.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

public class DeleteTask implements Command {
    private static DeleteTask instance;
    private final RequestFactory requestFactory;

    public static final String ID_PARAM_NAME = "id";

    private static final String COMMAND_TASK_LIST = "/controller?command=tasksPage";

    private final TaskService taskService;
    private DeleteTask(TaskService taskService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.taskService = taskService;
    }

    public static synchronized DeleteTask getInstance(TaskService taskService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new DeleteTask(taskService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        Long taskId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        taskService.delete(taskId);
        return requestFactory.createRedirectResponse(COMMAND_TASK_LIST);
    }
}
