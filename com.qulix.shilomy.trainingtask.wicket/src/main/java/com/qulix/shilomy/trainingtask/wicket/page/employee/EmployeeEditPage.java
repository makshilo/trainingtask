package com.qulix.shilomy.trainingtask.wicket.page.employee;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.form.EmployeeForm;
import com.qulix.shilomy.trainingtask.wicket.page.BasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class EmployeeEditPage extends BasePage {

    private static final String EMPLOYEE_FORM = "employeeForm";
    private static final String EMPTY_STRING = "";
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    public EmployeeEditPage(PageParameters parameters) {
        StringValue employeeId = parameters.get(EmployeeParam.ID.get());
        if (!employeeId.isEmpty()) {
            add(new EmployeeForm(EMPLOYEE_FORM, employeeService.get(employeeId.toLong())));
        } else {
            add(new EmployeeForm(EMPLOYEE_FORM, new EmployeeEntity(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING)));
        }
    }
}
