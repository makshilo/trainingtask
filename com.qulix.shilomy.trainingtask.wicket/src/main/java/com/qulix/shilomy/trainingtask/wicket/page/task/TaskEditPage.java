package com.qulix.shilomy.trainingtask.wicket.page.task;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import com.qulix.shilomy.trainingtask.wicket.panel.task.TaskFormPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class TaskEditPage extends BasePage {
    public static final String TASK_FORM_PANEL = "taskFormPanel";
    public static final String EMPTY_STRING = "";
    public static final long ZERO_LONG = 0L;
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    public TaskEditPage(PageParameters parameters) {
        StringValue taskId = parameters.get(TaskParam.ID.get());
        if (!taskId.isEmpty()) {
            add(new TaskFormPanel(TASK_FORM_PANEL, taskService.get(taskId.toLong())));
        } else {
            long projectId = ZERO_LONG;
            if ((boolean) getSession().getAttribute("projectLock")) {
                projectId = (long) getSession().getAttribute("lockedProject");
            }
            add(new TaskFormPanel(TASK_FORM_PANEL,
                    new TaskEntity(
                            TaskStatus.NOT_STARTED,
                            EMPTY_STRING,
                            projectId,
                            EMPTY_STRING,
                            null,
                            null,
                            ZERO_LONG)));
        }
    }
}
