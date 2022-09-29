package com.qulix.shilomy.trainingtask.web.command.impl.action.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.exception.NullFieldException;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class EditEmployee implements Command {
    private static EditEmployee instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String COMMAND_EMPLOYEE_LIST = "command/employees_page";
    public static final String EDIT_EMPLOYEE_PAGE = "page.editEmployee";


    private final EmployeeService employeeService;
    private EditEmployee(EmployeeService employeeService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.employeeService = employeeService;
    }

    public static synchronized EditEmployee getInstance(EmployeeService employeeService, RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new EditEmployee(employeeService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String patronymic = request.getParameter("patronymic");
        String position = request.getParameter("position");
        Long id = Long.parseLong(request.getParameter("id"));
        try {
            CreateEmployee.validateFields(request, firstName, lastName, patronymic, position);
        } catch (NullFieldException e) {
            return requestFactory.createForwardResponse(propertyContext.get(EDIT_EMPLOYEE_PAGE));
        }
        employeeService.update(new EmployeeEntity(firstName, lastName, patronymic, position, id));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_EMPLOYEE_LIST));
    }
}
