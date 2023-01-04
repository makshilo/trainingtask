package com.qulix.shilomy.trainingtask.wicket.page.project;

import com.qulix.shilomy.trainingtask.web.controller.project.ProjectParam;
import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.form.ProjectForm;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import com.qulix.shilomy.trainingtask.wicket.panel.task.TaskListPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class ProjectEditPage extends BasePage {
    public static final String PROJECT_FORM = "projectForm";
    public static final String EMPTY_STRING = "";
    public static final String PROJECT_LOCK = "projectLock";
    public static final String LOCKED_PROJECT = "lockedProject";

    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    public ProjectEditPage(PageParameters parameters) {
        StringValue projectId = parameters.get(ProjectParam.ID.get());
        if (!projectId.isEmpty()) {
            ProjectEntity project = projectService.get(projectId.toLong());
            add(new ProjectForm(PROJECT_FORM, project));
            add(new TaskListPanel(TaskParam.TASKS.get(), TaskServiceImpl.getInstance(TaskDao.getInstance()).findByProject(project)));
            getSession().setAttribute(PROJECT_LOCK, true).setAttribute(LOCKED_PROJECT, projectId.toLong());
        } else {
            ProjectEntity project = new ProjectEntity(EMPTY_STRING, EMPTY_STRING);
            add(new ProjectForm(PROJECT_FORM, project));
            add(new TaskListPanel(TaskParam.TASKS.get(), TaskServiceImpl.getInstance(TaskDao.getInstance()).findByProject(project)).setEnabled(false).setVisible(false));
        }
    }
}
