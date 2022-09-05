package com.qulix.shilomy.trainingtask.web.command.impl.action.task;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditTask implements Command {
    private static EditTask instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_TASK_LIST = "command/tasks_page";

    private final TaskService taskService;
    private EditTask(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.taskService = taskService;
    }

    public static synchronized EditTask getInstance(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new EditTask(taskService, requestFactory, propertyContext);
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
                new Date(prepareDate(request.getParameter("startYear"), request.getParameter("startMonth"), request.getParameter("startDay"))),
                new Date(prepareDate(request.getParameter("endYear"), request.getParameter("endMonth"), request.getParameter("endDay"))),
                Long.parseLong(request.getParameter("exec")),
                Long.parseLong(request.getParameter("id"))));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_TASK_LIST));
    }

    private long prepareDate(String year, String month, String day) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormatter.parse(year + "-" + month + "-" + day).getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
