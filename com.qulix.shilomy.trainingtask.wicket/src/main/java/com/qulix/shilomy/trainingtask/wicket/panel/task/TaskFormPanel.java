package com.qulix.shilomy.trainingtask.wicket.panel.task;

import com.qulix.shilomy.trainingtask.data.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.form.TaskForm;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.string.StringValue;

public class TaskFormPanel extends Panel {
    private static final String TASK_FORM = "taskForm";

    private static final String EMPTY_STRING = "";
    private static final long ZERO_LONG = 0L;
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    public TaskFormPanel(String id, StringValue taskId, StringValue projectId) {
        super(id);

        if (!taskId.isEmpty()) {
            if (!projectId.isEmpty()) {
                add(new TaskForm(TASK_FORM, taskService.get(taskId.toLong()), true));
            } else {
                add(new TaskForm(TASK_FORM, taskService.get(taskId.toLong()), false));
            }
        } else {
            if (!projectId.isEmpty()) {
                add(new TaskForm(TASK_FORM,
                        new TaskEntity(
                                TaskStatus.NOT_STARTED,
                                EMPTY_STRING,
                                projectId.toLong(),
                                EMPTY_STRING,
                                null,
                                null,
                                ZERO_LONG), true));

            } else {
                add(new TaskForm(TASK_FORM,
                        new TaskEntity(
                                TaskStatus.NOT_STARTED,
                                EMPTY_STRING,
                                ZERO_LONG,
                                EMPTY_STRING,
                                null,
                                null,
                                ZERO_LONG), false));
            }
        }
    }
}
