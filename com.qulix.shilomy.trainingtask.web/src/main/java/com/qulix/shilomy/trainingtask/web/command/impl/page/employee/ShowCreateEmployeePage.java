package com.qulix.shilomy.trainingtask.web.command.impl.page.employee;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;

public class ShowCreateEmployeePage implements Command {
    private static ShowCreateEmployeePage instance;

    private final RequestFactory requestFactory;


    private static final String CREATE_EMPLOYEE_PAGE = "/jsp/createEmployee.jsp";

    private ShowCreateEmployeePage(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public static synchronized ShowCreateEmployeePage getInstance(RequestFactory requestFactory) {
        if (instance == null) {
            instance = new ShowCreateEmployeePage(requestFactory);
        }
        return instance;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(CREATE_EMPLOYEE_PAGE);
    }
}
