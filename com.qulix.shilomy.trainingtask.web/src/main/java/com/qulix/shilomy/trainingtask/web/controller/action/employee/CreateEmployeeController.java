package com.qulix.shilomy.trainingtask.web.controller.action.employee;

import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createEmployee")
public class CreateEmployeeController extends HttpServlet {
    public static final String FIRST_NAME_PARAM_NAME = "firstName";
    public static final String LAST_NAME_PARAM_NAME = "lastName";
    public static final String PATRONYMIC_PARAM_NAME = "patronymic";
    public static final String POSITION_PARAM_NAME = "position";

    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
    public static final String FIRST_NAME_NULL = "firstNameNull";
    public static final String LAST_NAME_NULL = "lastNameNull";
    public static final String PATRONYMIC_NULL = "patronymicNull";
    public static final String POSITION_NULL = "positionNull";
    public static final String EMPTY_STRING = "";

    private static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";
    private static final String COMMAND_EMPLOYEE_LIST = "/employees";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter(FIRST_NAME_PARAM_NAME);
        String lastName = request.getParameter(LAST_NAME_PARAM_NAME);
        String patronymic = request.getParameter(PATRONYMIC_PARAM_NAME);
        String position = request.getParameter(POSITION_PARAM_NAME);

        if (validateFields(request, firstName, lastName, patronymic, position)) {
            employeeService.add(new EmployeeEntity(firstName, lastName, patronymic, position));
            response.sendRedirect(COMMAND_EMPLOYEE_LIST);
        } else {
            request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
        }
    }

    private boolean validateFields(HttpServletRequest request, String firstName, String lastName, String patronymic, String position){
        if (firstName == null || firstName.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, FIRST_NAME_NULL);
            return false;
        } else if (lastName == null || lastName.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, LAST_NAME_NULL);
            return false;
        } else if (patronymic == null || patronymic.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, PATRONYMIC_NULL);
            return false;
        } else if (position == null || position.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, POSITION_NULL);
            return false;
        }
        return true;
    }
}
