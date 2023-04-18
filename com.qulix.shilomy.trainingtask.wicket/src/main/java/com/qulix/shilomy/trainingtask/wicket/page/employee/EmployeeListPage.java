package com.qulix.shilomy.trainingtask.wicket.page.employee;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import com.qulix.shilomy.trainingtask.wicket.panel.employee.EmployeeListPanel;

public class EmployeeListPage extends BasePage {
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());
    public EmployeeListPage() {
        add(new EmployeeListPanel(EmployeeParam.EMPLOYEES.get(), employeeService.findAll()));
    }
}
