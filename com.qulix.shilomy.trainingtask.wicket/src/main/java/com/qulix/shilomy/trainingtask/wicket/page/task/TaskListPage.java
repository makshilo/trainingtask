package com.qulix.shilomy.trainingtask.wicket.page.task;

import com.qulix.shilomy.trainingtask.data.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import com.qulix.shilomy.trainingtask.wicket.panel.task.TaskListPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TaskListPage extends BasePage {
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    public TaskListPage() {
        add(new TaskListPanel(TaskParam.TASKS.get(), taskService.findAll(), new PageParameters()));
    }
}
