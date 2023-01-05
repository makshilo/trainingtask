package com.qulix.shilomy.trainingtask.wicket.page.project;

import com.qulix.shilomy.trainingtask.web.controller.project.ProjectParam;
import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import com.qulix.shilomy.trainingtask.wicket.panel.project.ProjectListPanel;

public class ProjectListPage extends BasePage {
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());
    public ProjectListPage() {
        add(new ProjectListPanel(ProjectParam.PROJECTS.get(), projectService.findAll()));
    }
}
