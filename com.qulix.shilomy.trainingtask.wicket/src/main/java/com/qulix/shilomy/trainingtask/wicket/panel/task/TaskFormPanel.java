package com.qulix.shilomy.trainingtask.wicket.panel.task;

import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.wicket.form.TaskForm;
import org.apache.wicket.markup.html.panel.Panel;

public class TaskFormPanel extends Panel {
    public static final String TASK_FORM = "taskForm";

    public TaskFormPanel(String id, TaskEntity task) {
        super(id);

        add(new TaskForm(TASK_FORM, task));
    }
}
