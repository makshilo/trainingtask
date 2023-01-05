package com.qulix.shilomy.trainingtask.wicket.link;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.wicket.page.employee.EmployeeEditPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class EmployeeEditLink extends Link<Void> {
    private final EmployeeEntity employee;
    private PageParameters parameters;

    public EmployeeEditLink(String id, EmployeeEntity employee, PageParameters parameters) {
        super(id);
        this.employee = employee;
        this.parameters = parameters;
    }

    @Override
    public void onClick() {
        if (employee.getId() != null) {
            parameters.add(EmployeeParam.ID.get(), employee.getId());
            setResponsePage(EmployeeEditPage.class, parameters);
        } else {
            setResponsePage(EmployeeEditPage.class, parameters);
        }
    }
}
