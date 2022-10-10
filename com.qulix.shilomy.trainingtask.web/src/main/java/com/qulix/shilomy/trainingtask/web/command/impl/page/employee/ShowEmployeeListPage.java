package com.qulix.shilomy.trainingtask.web.command.impl.page.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;


public class ShowEmployeeListPage implements Command {

    private static ShowEmployeeListPage instance;

    private final RequestFactory requestFactory;


    private static final String EMPLOYEES_PAGE = "/jsp/employeeList.jsp";


    private final EmployeeService employeeService;

    private ShowEmployeeListPage(EmployeeService employeeService, RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
        this.employeeService = employeeService;
    }

    public static synchronized ShowEmployeeListPage getInstance(EmployeeService employeeService, RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowEmployeeListPage(employeeService, requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        request.addAttributeToJsp("employees", employeeService.findAll());
        return requestFactory.createForwardResponse(EMPLOYEES_PAGE);
    }
}
