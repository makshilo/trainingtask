package com.qulix.shilomy.trainingtask.wicket.panel.task;

import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.wicket.link.TaskEditLink;
import com.qulix.shilomy.trainingtask.wicket.table.TaskTable;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class TaskListPanel extends Panel {
    private static final String EMPTY_STRING = "";
    private static final long ZERO_LONG = 0L;
    private static final String ADD_TASK = "addTask";

    public TaskListPanel(String id, List<TaskEntity> tasks, PageParameters parameters) {
        super(id);
        add(new TaskTable(TaskParam.TASKS.get(), tasks, parameters));
        add(new TaskEditLink(ADD_TASK,
                new TaskEntity(
                        TaskStatus.NOT_STARTED,
                        EMPTY_STRING,
                        ZERO_LONG,
                        EMPTY_STRING,
                        null,
                        null,
                        ZERO_LONG),
                parameters));
    }
}
