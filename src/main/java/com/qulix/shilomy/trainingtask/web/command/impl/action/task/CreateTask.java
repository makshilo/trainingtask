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

public class CreateTask implements Command {
    private static CreateTask instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_TASK_LIST = "command/tasks_page";

    private final TaskService taskService;

    private CreateTask(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.taskService = taskService;
    }

    public static synchronized CreateTask getInstance(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new CreateTask(taskService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        taskService.add(new TaskEntity(
                TaskStatus.of(request.getParameter("stat")),
                request.getParameter("tname"),
                Long.parseLong(request.getParameter("proj")),
                request.getParameter("work"),
                Date.valueOf(prepareDate(request.getParameter("startYear") , request.getParameter("startMonth"), request.getParameter("startDay"))),
                Date.valueOf(prepareDate(request.getParameter("endYear") , request.getParameter("endMonth"), request.getParameter("endDay"))),
                Long.parseLong(request.getParameter("exec"))));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_TASK_LIST));
    }

    private String prepareDate(String year, String month, String day){
        if(year.length() < 4){
            year = ("0000" + year).substring(year.length());
        }
        if (day.length() < 2){
            day = ("00" + day).substring(day.length());
        }
        return year+'-'+month+'-'+day;
    }
}
