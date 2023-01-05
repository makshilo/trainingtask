package com.qulix.shilomy.trainingtask.wicket.page.task;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import com.qulix.shilomy.trainingtask.wicket.panel.task.TaskFormPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class TaskEditPage extends BasePage {
    private static final String TASK_FORM_PANEL = "taskFormPanel";

    public TaskEditPage(PageParameters parameters) {
        StringValue taskId = parameters.get(TaskParam.ID.get());
        StringValue projectId = parameters.get(TaskParam.CURRENT_PROJECT.get());

        add(new TaskFormPanel(TASK_FORM_PANEL, taskId, projectId));
    }
}
