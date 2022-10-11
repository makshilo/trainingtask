package com.qulix.shilomy.trainingtask.web.command.impl.action.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class EditEmployee implements Command {
    public static final String FIRST_NAME_PARAM_NAME = "firstName";
    public static final String LAST_NAME_PARAM_NAME = "lastName";
    public static final String PATRONYMIC_PARAM_NAME = "patronymic";
    public static final String POSITION_PARAM_NAME = "position";
    public static final String ID_PARAM_NAME = "id";
    private static EditEmployee instance;

    private final RequestFactory requestFactory;

    private static final String COMMAND_EMPLOYEE_LIST = "/controller?command=employeesPage";
    public static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";


    private final EmployeeService employeeService;
    private EditEmployee(EmployeeService employeeService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.employeeService = employeeService;
    }

    public static synchronized EditEmployee getInstance(EmployeeService employeeService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new EditEmployee(employeeService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        String firstName = request.getParameter(FIRST_NAME_PARAM_NAME);
        String lastName = request.getParameter(LAST_NAME_PARAM_NAME);
        String patronymic = request.getParameter(PATRONYMIC_PARAM_NAME);
        String position = request.getParameter(POSITION_PARAM_NAME);
        Long id = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        if (CreateEmployee.validateFields(request, firstName, lastName, patronymic, position)) {
            employeeService.update(new EmployeeEntity(firstName, lastName, patronymic, position, id));
            return requestFactory.createRedirectResponse(COMMAND_EMPLOYEE_LIST);
        } else {
            return requestFactory.createForwardResponse(EDIT_EMPLOYEE_PAGE);
        }
    }
}
