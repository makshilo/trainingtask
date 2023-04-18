package com.qulix.shilomy.trainingtask.wicket.link;

import com.qulix.shilomy.trainingtask.data.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.task.TaskListPage;
import org.apache.wicket.markup.html.link.Link;

public class TaskDeleteLink extends Link<Void> {
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    private final TaskEntity task;

    public TaskDeleteLink(String id, TaskEntity task) {
        super(id);
        this.task = task;
    }

    @Override
    public void onClick() {
        taskService.delete(task.getId());
        setResponsePage(TaskListPage.class);
    }
}
