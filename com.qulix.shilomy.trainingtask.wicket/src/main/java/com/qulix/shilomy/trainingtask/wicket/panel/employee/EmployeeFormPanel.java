package com.qulix.shilomy.trainingtask.wicket.panel.employee;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.wicket.form.EmployeeForm;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.string.StringValue;

public class EmployeeFormPanel extends Panel {
    private static final String EMPLOYEE_FORM = "employeeForm";
    private static final String EMPTY_STRING = "";
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    public EmployeeFormPanel(String id, StringValue employeeId) {
        super(id);

        if (!employeeId.isEmpty()) {
            add(new EmployeeForm(EMPLOYEE_FORM, employeeService.get(employeeId.toLong())));
        } else {
            add(new EmployeeForm(EMPLOYEE_FORM, new EmployeeEntity(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING)));
        }
    }
}
