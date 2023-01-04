package com.qulix.shilomy.trainingtask.wicket.link;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.wicket.page.employee.EmployeeEditPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class EmployeeEditLink extends Link<Void> {
    private final EmployeeEntity employee;

    public EmployeeEditLink(String id, EmployeeEntity employee) {
        super(id);
        this.employee = employee;
    }

    @Override
    public void onClick() {
        PageParameters parameters = new PageParameters();
        parameters.add(EmployeeParam.ID.get(), employee.getId());
        setResponsePage(EmployeeEditPage.class, parameters);
    }
}
