package com.qulix.shilomy.trainingtask.wicket.link;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.wicket.page.task.TaskEditPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TaskEditLink extends Link<Void> {
    private final TaskEntity task;

    public TaskEditLink(String id, TaskEntity task) {
        super(id);
        this.task = task;
    }

    @Override
    public void onClick() {
        if (task.getId() != null) {
            PageParameters parameters = new PageParameters();
            parameters.add(TaskParam.ID.get(), task.getId());
            setResponsePage(TaskEditPage.class, parameters);
        } else {
            setResponsePage(TaskEditPage.class);
        }
    }
}
