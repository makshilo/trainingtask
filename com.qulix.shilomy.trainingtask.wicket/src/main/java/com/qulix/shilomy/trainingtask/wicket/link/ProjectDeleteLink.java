package com.qulix.shilomy.trainingtask.wicket.link;

import com.qulix.shilomy.trainingtask.data.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.project.ProjectListPage;
import org.apache.wicket.markup.html.link.Link;

public class ProjectDeleteLink extends Link<Void> {
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    private final ProjectEntity project;

    public ProjectDeleteLink(String id, ProjectEntity project) {
        super(id);
        this.project = project;
    }

    @Override
    public void onClick() {
        projectService.delete(project.getId());
        setResponsePage(ProjectListPage.class);
    }
}
