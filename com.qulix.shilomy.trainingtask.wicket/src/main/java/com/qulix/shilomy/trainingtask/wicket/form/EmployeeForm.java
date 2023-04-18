package com.qulix.shilomy.trainingtask.wicket.form;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.employee.EmployeeListPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class EmployeeForm extends Form<EmployeeEntity> {
    private static final String FIRST_NAME_LABEL = "Имя";
    private static final String FIRST_NAME_ERROR = "firstNameError";
    private static final String LAST_NAME_LABEL = "Фамилия";
    private static final String LAST_NAME_ERROR = "lastNameError";
    private static final String PATRONYMIC_LABEL = "Отчество";
    private static final String PATRONYMIC_ERROR = "patronymicError";
    private static final String POSITION_LABEL = "Должность";
    private static final String POSITION_ERROR = "positionError";
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    public EmployeeForm(String id, EmployeeEntity employee) {
        super(id, new CompoundPropertyModel<>(employee));

        add(new TextField<String>(EmployeeParam.FIRST_NAME.get(), new PropertyModel<>(getModelObject(), EmployeeParam.FIRST_NAME.get()))
                .setLabel(Model.of(FIRST_NAME_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(FIRST_NAME_ERROR, get(EmployeeParam.FIRST_NAME.get())));

        add(new TextField<String>(EmployeeParam.LAST_NAME.get(), new PropertyModel<>(getModelObject(), EmployeeParam.LAST_NAME.get()))
                .setLabel(Model.of(LAST_NAME_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(LAST_NAME_ERROR, get(EmployeeParam.LAST_NAME.get())));

        add(new TextField<String>(EmployeeParam.PATRONYMIC.get(), new PropertyModel<>(getModelObject(), EmployeeParam.PATRONYMIC.get()))
                .setLabel(Model.of(PATRONYMIC_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(PATRONYMIC_ERROR, get(EmployeeParam.PATRONYMIC.get())));

        add(new TextField<String>(EmployeeParam.POSITION.get(), new PropertyModel<>(getModelObject(), EmployeeParam.POSITION.get()))
                .setLabel(Model.of(POSITION_LABEL))
                .setRequired(true));
        add(new ComponentFeedbackPanel(POSITION_ERROR, get(EmployeeParam.POSITION.get())));
    }

    @Override
    protected void onSubmit() {
        EmployeeEntity employee = getModelObject();

        if (employee.getId() != null) {
            employeeService.update(getModelObject());
            setResponsePage(EmployeeListPage.class);
        } else {
            employeeService.add(getModelObject());
            setResponsePage(EmployeeListPage.class);
        }
    }
}