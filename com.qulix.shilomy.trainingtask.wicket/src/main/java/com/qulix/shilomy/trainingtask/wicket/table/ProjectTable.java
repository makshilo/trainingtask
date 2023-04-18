package com.qulix.shilomy.trainingtask.wicket.table;

import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.param.ProjectParam;
import com.qulix.shilomy.trainingtask.wicket.link.ProjectDeleteLink;
import com.qulix.shilomy.trainingtask.wicket.link.ProjectEditLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class ProjectTable extends ListView<ProjectEntity> {

    private static final String NAME = "name";
    private static final String EDIT_PROJECT = "editProject";
    private static final String DELETE_PROJECT = "deleteProject";

    public ProjectTable(String id, List<ProjectEntity> list) {
        super(id, list);
    }

    @Override
    public void populateItem(ListItem<ProjectEntity> item) {
        ProjectEntity project = item.getModelObject();
        item.add(new Label(NAME, project.getName()));
        item.add(new MultiLineLabel(ProjectParam.DESCRIPTION.get(), project.getDescription()));
        item.add(new ProjectEditLink(EDIT_PROJECT, project, new PageParameters()));
        item.add(new ProjectDeleteLink(DELETE_PROJECT, project));
    }
}
