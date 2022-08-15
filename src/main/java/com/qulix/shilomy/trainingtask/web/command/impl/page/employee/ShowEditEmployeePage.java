package com.qulix.shilomy.trainingtask.web.command.impl.page.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class ShowEditEmployeePage implements Command {
    private static ShowEditEmployeePage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String EMPLOYEE_EDIT_PAGE = "page.editEmployee";

    private final EmployeeService employeeService;
    private ShowEditEmployeePage(EmployeeService employeeService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.employeeService = employeeService;
    }

    public static synchronized ShowEditEmployeePage getInstance(EmployeeService employeeService, RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowEditEmployeePage(employeeService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        request.addAttributeToJsp("employee", employeeService.get(Long.parseLong(request.getParameter("id"))));
        return requestFactory.createForwardResponse(propertyContext.get(EMPLOYEE_EDIT_PAGE));
    }
}
