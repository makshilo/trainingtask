package com.qulix.shilomy.trainingtask.web.command.impl.action.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.exception.NullFieldException;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class CreateEmployee implements Command {
    private static CreateEmployee instance;

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    private static final String COMMAND_EMPLOYEE_LIST = "command/employees_page";
    public static final String CREATE_EMPLOYEE_PAGE = "page.createEmployee";

    private final EmployeeService employeeService;

    public static final String FIRST_NAME_NULL = "firstNameNull";
    public static final String EMPLOYEE_FIRST_NAME_NULL_MESSAGE = "Employee first name is null";
    public static final String LAST_NAME_NULL = "lastNameNull";
    public static final String EMPLOYEE_LAST_NAME_NULL_MESSAGE = "Employee last name is null";
    public static final String PATRONYMIC_NULL = "patronymicNull";
    public static final String EMPLOYEE_PATRONYMIC_NULL_MESSAGE = "Employee patronymic is null";
    public static final String POSITION_NULL = "positionNull";
    public static final String EMPLOYEE_POSITION_NULL_MESSAGE = "Employee position is null";

    private CreateEmployee(EmployeeService employeeService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.employeeService = employeeService;
    }

    public static synchronized CreateEmployee getInstance(EmployeeService employeeService, RequestFactory requestFactory, PropertyContext propertyContext) {
        if (instance == null) {
            instance = new CreateEmployee(employeeService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String patronymic = request.getParameter("patronymic");
        String position = request.getParameter("position");
        try {
            validateFields(request, firstName, lastName, patronymic, position);
        } catch (NullFieldException e) {
            return requestFactory.createForwardResponse(propertyContext.get(CREATE_EMPLOYEE_PAGE));
        }
        employeeService.add(new EmployeeEntity(firstName, lastName, patronymic, position));
        return requestFactory.createRedirectResponse(propertyContext.get(COMMAND_EMPLOYEE_LIST));
    }

    static void validateFields(CommandRequest request, String firstName, String lastName, String patronymic, String position) throws NullFieldException {
        if (firstName == null || firstName.equals("")) {
            request.addAttributeToJsp(FIRST_NAME_NULL, true);
            throw new NullFieldException(EMPLOYEE_FIRST_NAME_NULL_MESSAGE);
        } else if (lastName == null || lastName.equals("")) {
            request.addAttributeToJsp(LAST_NAME_NULL, true);
            throw new NullFieldException(EMPLOYEE_LAST_NAME_NULL_MESSAGE);
        } else if (patronymic == null || patronymic.equals("")) {
            request.addAttributeToJsp(PATRONYMIC_NULL, true);
            throw new NullFieldException(EMPLOYEE_PATRONYMIC_NULL_MESSAGE);
        } else if (position == null || position.equals("")) {
            request.addAttributeToJsp(POSITION_NULL, true);
            throw new NullFieldException(EMPLOYEE_POSITION_NULL_MESSAGE);
        }
    }
}
