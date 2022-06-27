package com.qulix.shilomy.trainingtask.web.command.impl.page.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.dao.TaskDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodTaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.TaskService;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;

import java.util.List;

public class ShowTaskListPage implements Command {
    private static ShowTaskListPage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String TASKS_PAGE = "page.tasks";

    private final TaskService taskService;

    private ShowTaskListPage(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        TaskDao taskDao = MethodTaskDao.getInstance();
        taskService = TaskServiceImpl.getInstance(taskDao);
    }

    public static synchronized ShowTaskListPage getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowTaskListPage(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<TaskEntity> tasks = taskService.findAll();
        request.addAttributeToJsp("tasks", tasks);
        return requestFactory.createForwardResponse(propertyContext.get(TASKS_PAGE));
    }
}
