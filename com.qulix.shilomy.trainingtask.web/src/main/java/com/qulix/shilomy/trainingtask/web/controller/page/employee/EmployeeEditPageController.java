package com.qulix.shilomy.trainingtask.web.controller.page.employee;

import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editEmployeePage")
public class EmployeeEditPageController extends HttpServlet {
    public static final String EMPLOYEE_PARAM_NAME = "employee";
    public static final String ID_PARAM_NAME = "id";
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String EDIT_MODE = "edit";

    private static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
        EmployeeEntity employee = employeeService.get(Long.parseLong(request.getParameter(ID_PARAM_NAME)));
        request.setAttribute(EMPLOYEE_PARAM_NAME, employee);
        request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
