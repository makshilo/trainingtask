package com.qulix.shilomy.trainingtask.web.controller.page.employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createEmployeePage")
public class EmployeeCreatePageController extends HttpServlet {
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String CREATE_MODE = "create";

    private static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(PAGE_MODE_PARAM_NAME, CREATE_MODE);
        request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
