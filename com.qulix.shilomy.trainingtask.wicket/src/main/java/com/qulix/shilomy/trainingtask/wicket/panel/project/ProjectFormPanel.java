package com.qulix.shilomy.trainingtask.wicket.panel.project;

import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.form.ProjectForm;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.string.StringValue;

public class ProjectFormPanel extends Panel {
    private static final String PROJECT_FORM = "projectForm";
    private static final String EMPTY_STRING = "";

    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    public ProjectFormPanel(String id, StringValue projectId) {
        super(id);

        if (!projectId.isEmpty()) {
            ProjectEntity project = projectService.get(projectId.toLong());
            add(new ProjectForm(PROJECT_FORM, project));
        } else {
            ProjectEntity project = new ProjectEntity(EMPTY_STRING, EMPTY_STRING);
            add(new ProjectForm(PROJECT_FORM, project));
        }
    }
}
