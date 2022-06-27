package com.qulix.shilomy.trainingtask.web.command.impl.action.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.dao.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodEmployeeDao;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;

public class DeleteEmployee implements Command {
    private static DeleteEmployee instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_EMPLOYEE_LIST = "command/employees_page";

    private final EmployeeService employeeService;
    private DeleteEmployee(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        EmployeeDao employeeDao = MethodEmployeeDao.getInstance();
        employeeService = EmployeeServiceImpl.getInstance(employeeDao);
    }

    public static synchronized DeleteEmployee getInstance(RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new DeleteEmployee(requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        employeeService.delete(Long.parseLong(request.getParameter("id")));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_EMPLOYEE_LIST));
    }
}
