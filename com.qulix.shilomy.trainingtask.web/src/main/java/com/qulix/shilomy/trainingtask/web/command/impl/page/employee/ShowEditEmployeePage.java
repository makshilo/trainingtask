package com.qulix.shilomy.trainingtask.web.command.impl.page.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class ShowEditEmployeePage implements Command {
    private static ShowEditEmployeePage instance;

    private final RequestFactory requestFactory;

    private static final String EMPLOYEE_EDIT_PAGE = "/jsp/editEmployee.jsp";

    private final EmployeeService employeeService;
    private ShowEditEmployeePage(EmployeeService employeeService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.employeeService = employeeService;
    }

    public static synchronized ShowEditEmployeePage getInstance(EmployeeService employeeService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowEditEmployeePage(employeeService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        request.addAttributeToJsp("employee", employeeService.get(Long.parseLong(request.getParameter("id"))));
        return requestFactory.createForwardResponse(EMPLOYEE_EDIT_PAGE);
    }
}
