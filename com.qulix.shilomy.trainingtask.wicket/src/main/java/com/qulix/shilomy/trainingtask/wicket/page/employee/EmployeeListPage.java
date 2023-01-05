package com.qulix.shilomy.trainingtask.wicket.page.employee;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import com.qulix.shilomy.trainingtask.wicket.panel.employee.EmployeeListPanel;

public class EmployeeListPage extends BasePage {
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());
    public EmployeeListPage() {
        add(new EmployeeListPanel(EmployeeParam.EMPLOYEES.get(), employeeService.findAll()));
    }
}
