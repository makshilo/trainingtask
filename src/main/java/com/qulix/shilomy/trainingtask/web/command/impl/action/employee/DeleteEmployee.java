package com.qulix.shilomy.trainingtask.web.command.impl.action.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class DeleteEmployee implements Command {
    private static DeleteEmployee instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_EMPLOYEE_LIST = "command/employees_page";

    private final EmployeeService employeeService;
    private DeleteEmployee(EmployeeService employeeService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.employeeService = employeeService;
    }

    public static synchronized DeleteEmployee getInstance(EmployeeService employeeService, RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new DeleteEmployee(employeeService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        employeeService.delete(Long.parseLong(request.getParameter("id")));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_EMPLOYEE_LIST));
    }
}
