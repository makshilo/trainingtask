package com.qulix.shilomy.trainingtask.wicket.panel.employee;

import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.wicket.link.EmployeeEditLink;
import com.qulix.shilomy.trainingtask.wicket.table.EmployeeTable;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

public class EmployeeListPanel extends Panel {
    private static final String ADD_EMPLOYEE = "addEmployee";
    private static final String EMPTY_STRING = "";

    public EmployeeListPanel(String id, List<EmployeeEntity> employees) {
        super(id);

        add(new EmployeeTable(EmployeeParam.EMPLOYEES.get(), employees));
        add(new EmployeeEditLink(ADD_EMPLOYEE,
                new EmployeeEntity(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING),
                new PageParameters()));
    }
}
