package com.qulix.shilomy.trainingtask.wicket.page.project;

import com.qulix.shilomy.trainingtask.web.controller.project.ProjectParam;
import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import com.qulix.shilomy.trainingtask.wicket.panel.project.ProjectFormPanel;
import com.qulix.shilomy.trainingtask.wicket.panel.task.TaskListPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class ProjectEditPage extends BasePage {
    private static final String EMPTY_STRING = "";
    private static final String PROJECT_FORM_PANEL = "projectFormPanel";

    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    public ProjectEditPage(PageParameters parameters) {
        StringValue projectId = parameters.get(ProjectParam.ID.get());
        PageParameters newParameters = new PageParameters();

        add(new ProjectFormPanel(PROJECT_FORM_PANEL, projectId));

        if (!projectId.isEmpty()) {
            newParameters.add(TaskParam.CURRENT_PROJECT.get(), projectId.toLong());
            add(new TaskListPanel(TaskParam.TASKS.get(),
                    TaskServiceImpl.getInstance(TaskDao.getInstance()).findByProject(projectService.get(projectId.toLong())),
                    newParameters));
        } else {
            add(new TaskListPanel(TaskParam.TASKS.get(),
                    TaskServiceImpl.getInstance(TaskDao.getInstance()).findByProject(new ProjectEntity(EMPTY_STRING, EMPTY_STRING)),
                    newParameters).setEnabled(false).setVisible(false));
        }
    }
}
