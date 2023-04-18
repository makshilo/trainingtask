package com.qulix.shilomy.trainingtask.wicket.link;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.employee.EmployeeListPage;
import org.apache.wicket.markup.html.link.Link;

public class EmployeeDeleteLink extends Link<Void> {
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    private final EmployeeEntity employee;
    public EmployeeDeleteLink(String id, EmployeeEntity employee) {
        super(id);
        this.employee = employee;
    }

    @Override
    public void onClick() {
        employeeService.delete(employee.getId());
        setResponsePage(EmployeeListPage.class);
    }
}
