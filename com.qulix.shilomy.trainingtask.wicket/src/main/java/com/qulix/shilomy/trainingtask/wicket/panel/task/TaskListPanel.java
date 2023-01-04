package com.qulix.shilomy.trainingtask.wicket.panel.task;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.wicket.link.TaskEditLink;
import com.qulix.shilomy.trainingtask.wicket.table.TaskTable;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.List;

public class TaskListPanel extends Panel {
    public static final String EMPTY_STRING = "";
    public static final long ZERO_LONG = 0L;
    public static final String ADD_TASK = "addTask";

    public TaskListPanel(String id, List<TaskEntity> tasks) {
        super(id);

        add(new TaskTable(TaskParam.TASKS.get(), tasks));
        add(new TaskEditLink(ADD_TASK,
                new TaskEntity(
                        TaskStatus.NOT_STARTED,
                        EMPTY_STRING,
                        ZERO_LONG,
                        EMPTY_STRING,
                        null,
                        null,
                        ZERO_LONG)));
    }
}
