package com.qulix.shilomy.trainingtask.web.command.impl.page.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.dao.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodEmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;

public class ShowEditEmployeePage implements Command {
    private static ShowEditEmployeePage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String EMPLOYEE_EDIT_PAGE = "page.editEmployee";

    private final EmployeeService employeeService;
    private ShowEditEmployeePage(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        EmployeeDao employeeDao = MethodEmployeeDao.getInstance();
        employeeService = EmployeeServiceImpl.getInstance(employeeDao);
    }

    public static synchronized ShowEditEmployeePage getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowEditEmployeePage(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String id = request.getParameter("id");
        final EmployeeEntity employee = employeeService.get(Long.parseLong(id));
        request.addAttributeToJsp("employee", employee);
        return requestFactory.createForwardResponse(propertyContext.get(EMPLOYEE_EDIT_PAGE));
    }
}
