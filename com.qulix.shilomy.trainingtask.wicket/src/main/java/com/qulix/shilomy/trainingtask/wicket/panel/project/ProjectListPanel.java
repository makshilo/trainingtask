package com.qulix.shilomy.trainingtask.wicket.panel.project;

import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.param.ProjectParam;
import com.qulix.shilomy.trainingtask.wicket.link.ProjectEditLink;
import com.qulix.shilomy.trainingtask.wicket.table.ProjectTable;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class ProjectListPanel extends Panel {

    private static final String ADD_PROJECT = "addProject";
    private static final String EMPTY_STRING = "";

    public ProjectListPanel(String id, List<ProjectEntity> projects) {
        super(id);

        add(new ProjectTable(ProjectParam.PROJECTS.get(), projects));
        add(new ProjectEditLink(ADD_PROJECT, new ProjectEntity(EMPTY_STRING, EMPTY_STRING), new PageParameters()));
    }
}
