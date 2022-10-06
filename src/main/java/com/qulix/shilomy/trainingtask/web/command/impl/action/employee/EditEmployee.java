package com.qulix.shilomy.trainingtask.web.command.impl.action.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.exception.NullFieldException;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;

public class EditEmployee implements Command {
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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String patronymic = request.getParameter("patronymic");
        String position = request.getParameter("position");
        Long id = Long.parseLong(request.getParameter("id"));
        try {
            CreateEmployee.validateFields(request, firstName, lastName, patronymic, position);
        } catch (NullFieldException e) {
            return requestFactory.createForwardResponse(EDIT_EMPLOYEE_PAGE);
        }
        employeeService.update(new EmployeeEntity(firstName, lastName, patronymic, position, id));
        return requestFactory.createRedirectResponse(COMMAND_EMPLOYEE_LIST);
    }
}
