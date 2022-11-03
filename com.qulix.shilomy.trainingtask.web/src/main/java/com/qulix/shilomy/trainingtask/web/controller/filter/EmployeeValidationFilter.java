package com.qulix.shilomy.trainingtask.web.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "EmployeeValidationFilter", urlPatterns = {"/createEmployee", "/editEmployee"})
public class EmployeeValidationFilter implements Filter {
    public static final String FIRST_NAME_PARAM_NAME = "firstName";
    public static final String LAST_NAME_PARAM_NAME = "lastName";
    public static final String PATRONYMIC_PARAM_NAME = "patronymic";
    public static final String POSITION_PARAM_NAME = "position";

    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String EDIT_MODE = "edit";

    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
    public static final String FIRST_NAME_NULL = "Имя не заполнено";
    public static final String LAST_NAME_NULL = "Фамилмя не заполнена";
    public static final String PATRONYMIC_NULL = "Отчество не заполнено";
    public static final String POSITION_NULL = "Должность не заполнена";
    public static final String EMPTY_STRING = "";

    public static final String EDIT_EMPLOYEE = "/editEmployee";
    private static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String page = ((HttpServletRequest)request).getRequestURI();
        if (page.equals(EDIT_EMPLOYEE)) {
            request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
        }

        String firstName = request.getParameter(FIRST_NAME_PARAM_NAME);
        String lastName = request.getParameter(LAST_NAME_PARAM_NAME);
        String patronymic = request.getParameter(PATRONYMIC_PARAM_NAME);
        String position = request.getParameter(POSITION_PARAM_NAME);

        if (firstName == null || firstName.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, FIRST_NAME_NULL);
            request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
        } else if (lastName == null || lastName.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, LAST_NAME_NULL);
            request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
        } else if (patronymic == null || patronymic.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, PATRONYMIC_NULL);
            request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
        } else if (position == null || position.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, POSITION_NULL);
            request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
