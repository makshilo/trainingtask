package com.qulix.shilomy.trainingtask.wicket.link;

import com.qulix.shilomy.trainingtask.web.controller.project.ProjectParam;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.wicket.page.project.ProjectEditPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ProjectEditLink extends Link<ProjectEntity> {
    private final ProjectEntity project;

    public ProjectEditLink(String id, ProjectEntity project) {
        super(id);
        this.project = project;
    }

    @Override
    public void onClick() {
        PageParameters parameters = new PageParameters();
        parameters.add(ProjectParam.ID.get(), project.getId());
        setResponsePage(ProjectEditPage.class, parameters);
    }
}
