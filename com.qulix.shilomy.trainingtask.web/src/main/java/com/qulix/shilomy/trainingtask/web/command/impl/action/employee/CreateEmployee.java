package com.qulix.shilomy.trainingtask.web.command.impl.action.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class CreateEmployee implements Command {
    private static CreateEmployee instance;

    private final RequestFactory requestFactory;

    private static final String COMMAND_EMPLOYEE_LIST = "/controller?command=employeesPage";
    public static final String CREATE_EMPLOYEE_PAGE = "/jsp/createEmployee.jsp";

    private final EmployeeService employeeService;

    public static final String FIRST_NAME_NULL = "firstNameNull";
    public static final String LAST_NAME_NULL = "lastNameNull";
    public static final String PATRONYMIC_NULL = "patronymicNull";
    public static final String POSITION_NULL = "positionNull";

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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String patronymic = request.getParameter("patronymic");
        String position = request.getParameter("position");

        if (validateFields(request, firstName, lastName, patronymic, position)) {
            employeeService.add(new EmployeeEntity(firstName, lastName, patronymic, position));
            return requestFactory.createRedirectResponse(COMMAND_EMPLOYEE_LIST);
        } else {
            return requestFactory.createForwardResponse(CREATE_EMPLOYEE_PAGE);
        }


    }

    static boolean validateFields(CommandRequest request, String firstName, String lastName, String patronymic, String position){
        if (firstName == null || firstName.equals("")) {
            request.addAttributeToJsp(FIRST_NAME_NULL, true);
            return false;
        } else if (lastName == null || lastName.equals("")) {
            request.addAttributeToJsp(LAST_NAME_NULL, true);
            return false;
        } else if (patronymic == null || patronymic.equals("")) {
            request.addAttributeToJsp(PATRONYMIC_NULL, true);
            return false;
        } else if (position == null || position.equals("")) {
            request.addAttributeToJsp(POSITION_NULL, true);
            return false;
        }
        return true;
    }
}
