package com.qulix.shilomy.trainingtask.web.command.impl.action.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class DeleteEmployee implements Command {
    private static DeleteEmployee instance;
    private final RequestFactory requestFactory;

    public static final String ID_PARAM_NAME = "id";

    private static final String COMMAND_EMPLOYEE_LIST = "/controller?command=employeesPage";

    private final EmployeeService employeeService;

    private DeleteEmployee(EmployeeService employeeService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.employeeService = employeeService;
    }

    public static synchronized DeleteEmployee getInstance(EmployeeService employeeService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new DeleteEmployee(employeeService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        Long employeeId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        employeeService.delete(employeeId);
        return requestFactory.createRedirectResponse(COMMAND_EMPLOYEE_LIST);
    }
}