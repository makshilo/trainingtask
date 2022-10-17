package com.qulix.shilomy.trainingtask.web.command.impl.action.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class CreateEmployee implements Command {
    public static final String FIRST_NAME_PARAM_NAME = "firstName";
    public static final String LAST_NAME_PARAM_NAME = "lastName";
    public static final String PATRONYMIC_PARAM_NAME = "patronymic";
    public static final String POSITION_PARAM_NAME = "position";
    private static CreateEmployee instance;
    private final RequestFactory requestFactory;

    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
    public static final String FIRST_NAME_NULL = "firstNameNull";
    public static final String LAST_NAME_NULL = "lastNameNull";
    public static final String PATRONYMIC_NULL = "patronymicNull";
    public static final String POSITION_NULL = "positionNull";
    public static final String EMPTY_STRING = "";

    private static final String COMMAND_EMPLOYEE_LIST = "/controller?command=employeesPage";
    public static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";

    private final EmployeeService employeeService;

    private CreateEmployee(EmployeeService employeeService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.employeeService = employeeService;
    }

    public static synchronized CreateEmployee getInstance(EmployeeService employeeService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new CreateEmployee(employeeService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        String firstName = request.getParameter(FIRST_NAME_PARAM_NAME);
        String lastName = request.getParameter(LAST_NAME_PARAM_NAME);
        String patronymic = request.getParameter(PATRONYMIC_PARAM_NAME);
        String position = request.getParameter(POSITION_PARAM_NAME);

        if (validateFields(request, firstName, lastName, patronymic, position)) {
            employeeService.add(new EmployeeEntity(firstName, lastName, patronymic, position));
            return requestFactory.createRedirectResponse(COMMAND_EMPLOYEE_LIST);
        } else {
            return requestFactory.createForwardResponse(EDIT_EMPLOYEE_PAGE);
        }
    }

    static boolean validateFields(CommandRequest request, String firstName, String lastName, String patronymic, String position){
        if (firstName == null || firstName.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(VALIDATION_ERROR_PARAM_NAME, FIRST_NAME_NULL);
            return false;
        } else if (lastName == null || lastName.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(VALIDATION_ERROR_PARAM_NAME, LAST_NAME_NULL);
            return false;
        } else if (patronymic == null || patronymic.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(VALIDATION_ERROR_PARAM_NAME, PATRONYMIC_NULL);
            return false;
        } else if (position == null || position.equals(EMPTY_STRING)) {
            request.addAttributeToJsp(VALIDATION_ERROR_PARAM_NAME, POSITION_NULL);
            return false;
        }
        return true;
    }
}
