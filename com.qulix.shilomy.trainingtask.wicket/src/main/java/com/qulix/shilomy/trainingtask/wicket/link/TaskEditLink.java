package com.qulix.shilomy.trainingtask.wicket.link;

import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.wicket.page.task.TaskEditPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TaskEditLink extends Link<Void> {
    private final TaskEntity task;
    private final PageParameters parameters;

    public TaskEditLink(String id, TaskEntity task, PageParameters parameters) {
        super(id);
        this.task = task;
        this.parameters = parameters;
    }

    @Override
    public void onClick() {
        if (task.getId() != null) {
            parameters.add(TaskParam.ID.get(), task.getId());
            setResponsePage(TaskEditPage.class, parameters);
        } else {
            setResponsePage(TaskEditPage.class, parameters);
        }
    }
}
