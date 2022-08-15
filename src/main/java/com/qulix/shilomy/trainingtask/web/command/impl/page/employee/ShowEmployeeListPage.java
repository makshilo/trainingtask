package com.qulix.shilomy.trainingtask.web.command.impl.page.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;


public class ShowEmployeeListPage implements Command {

    private static ShowEmployeeListPage instance;

    private final RequestFactory requestFactory;

    private final PropertyContext propertyContext;

    private static final String EMPLOYEES_PAGE = "page.employees";


    private final EmployeeService employeeService;

    private ShowEmployeeListPage(EmployeeService employeeService,
                                 RequestFactory requestFactory,
                                 PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.employeeService = employeeService;
    }

    public static synchronized ShowEmployeeListPage getInstance(EmployeeService employeeService,
                                                                RequestFactory requestFactory,
                                                                PropertyContext propertyContext) {
        if (instance == null) {
            instance = new ShowEmployeeListPage(employeeService, requestFactory, propertyContext);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        request.addAttributeToJsp("employees", employeeService.findAll());
        return requestFactory.createForwardResponse(propertyContext.get(EMPLOYEES_PAGE));
    }
}
