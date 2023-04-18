package com.qulix.shilomy.trainingtask.wicket.page.employee;

import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import com.qulix.shilomy.trainingtask.wicket.panel.employee.EmployeeFormPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class EmployeeEditPage extends BasePage {

    private static final String EMPLOYEE_FORM_PANEL = "employeeFormPanel";

    public EmployeeEditPage(PageParameters parameters) {
        StringValue employeeId = parameters.get(EmployeeParam.ID.get());

        add(new EmployeeFormPanel(EMPLOYEE_FORM_PANEL, employeeId));
    }
}
