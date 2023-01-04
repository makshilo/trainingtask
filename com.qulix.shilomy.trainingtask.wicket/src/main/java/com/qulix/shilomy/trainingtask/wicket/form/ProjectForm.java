package com.qulix.shilomy.trainingtask.wicket.form;

import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.project.ProjectListPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class ProjectForm extends Form<ProjectEntity> {
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

    private static final String NAME_LABEL = "Наименование";
    private static final String DESCRIPTION_LABEL = "Описание";

    private static final String NAME_ERROR = "nameError";
    private static final String DESCRIPTION_ERROR = "descriptionError";

    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    public ProjectForm(String id, ProjectEntity project) {
        super(id, new CompoundPropertyModel<>(project));

        add(new TextField<String>(NAME, new PropertyModel<>(getModelObject(), NAME))
                .setLabel(Model.of(NAME_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(NAME_ERROR, get(NAME)));

        add(new TextArea<String>(DESCRIPTION, new PropertyModel<>(getModelObject(), DESCRIPTION))
                .setLabel(Model.of(DESCRIPTION_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(DESCRIPTION_ERROR, get(DESCRIPTION)));
    }

    @Override
    protected void onSubmit() {
        ProjectEntity project = getModelObject();

        if (project.getId() != null) {
            projectService.update(getModelObject());
            setResponsePage(ProjectListPage.class);
        } else {
            projectService.add(getModelObject());
            setResponsePage(ProjectListPage.class);
        }
    }
}
