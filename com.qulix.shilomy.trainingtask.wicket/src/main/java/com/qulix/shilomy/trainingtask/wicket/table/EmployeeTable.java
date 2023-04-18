package com.qulix.shilomy.trainingtask.wicket.table;

import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.wicket.link.EmployeeDeleteLink;
import com.qulix.shilomy.trainingtask.wicket.link.EmployeeEditLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class EmployeeTable extends ListView<EmployeeEntity> {

    private static final String EDIT_EMPLOYEE = "editEmployee";
    private static final String DELETE_EMPLOYEE = "deleteEmployee";

    public EmployeeTable(String id, List<EmployeeEntity> list) {
        super(id, list);
    }

    @Override
    protected void populateItem(ListItem<EmployeeEntity> item) {
        EmployeeEntity employee = item.getModelObject();
        item.add(new Label(EmployeeParam.LAST_NAME.get(), employee.getLastName()));
        item.add(new Label(EmployeeParam.FIRST_NAME.get(), employee.getFirstName()));
        item.add(new Label(EmployeeParam.PATRONYMIC.get(), employee.getPatronymic()));
        item.add(new Label(EmployeeParam.POSITION.get(), employee.getPosition()));
        item.add(new EmployeeEditLink(EDIT_EMPLOYEE, employee, new PageParameters()));
        item.add(new EmployeeDeleteLink(DELETE_EMPLOYEE, employee));
    }
}
