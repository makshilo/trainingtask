package com.qulix.shilomy.trainingtask.web.controller;

import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employees")
public class EmployeeController extends HttpServlet {

    public static final String ACTION_PARAM_NAME = "action";
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String EMPLOYEE_PARAM_NAME = "employee";
    public static final String ID_PARAM_NAME = "id";
    public static final String FIRST_NAME_PARAM_NAME = "firstName";
    public static final String LAST_NAME_PARAM_NAME = "lastName";
    public static final String PATRONYMIC_PARAM_NAME = "patronymic";
    public static final String POSITION_PARAM_NAME = "position";
    public static final String EMPLOYEE_LIST_ACTION_NAME = "employeeList";
    public static final String CREATE_EMPLOYEE_PAGE_ACTION_NAME = "createEmployeePage";
    public static final String CREATE_EMPLOYEE_ACTION_NAME = "createEmployee";
    public static final String EDIT_EMPLOYEE_PAGE_ACTION_NAME = "editEmployeePage";
    public static final String EDIT_EMPLOYEE_ACTION_NAME = "editEmployee";
    public static final String DELETE_EMPLOYEE_ACTION_NAME = "deleteEmployee";
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String CREATE_MODE = "create";
    public static final String EDIT_MODE = "edit";

    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
    public static final String FIRST_NAME_NULL = "firstNameNull";
    public static final String LAST_NAME_NULL = "lastNameNull";
    public static final String PATRONYMIC_NULL = "patronymicNull";
    public static final String POSITION_NULL = "positionNull";
    public static final String EMPTY_STRING = "";

    private static final String EMPLOYEES_PAGE = "/jsp/employeeList.jsp";
    private static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";
    private static final String COMMAND_EMPLOYEE_LIST = "/employees?action=employeeList";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION_PARAM_NAME);

        switch (action) {
            case EMPLOYEE_LIST_ACTION_NAME:
                employeeList(request, response);
                break;
            case CREATE_EMPLOYEE_PAGE_ACTION_NAME:
                showEmployeeForm(request, response, CREATE_MODE);
                break;
            case CREATE_EMPLOYEE_ACTION_NAME:
                createEmployee(request, response);
                break;
            case EDIT_EMPLOYEE_PAGE_ACTION_NAME:
                showEmployeeForm(request, response, EDIT_MODE);
                break;
            case EDIT_EMPLOYEE_ACTION_NAME:
                editEmployee(request, response);
                break;
            case DELETE_EMPLOYEE_ACTION_NAME:
                deleteProject(request, response);
                break;
        }
    }

    private void employeeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(EMPLOYEES_PARAM_NAME, employeeService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher(EMPLOYEES_PAGE);
        dispatcher.forward(request, response);
    }

    private void showEmployeeForm(HttpServletRequest request, HttpServletResponse response, String pageMode) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE);
        if (pageMode.equals(EDIT_MODE)) {
            request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
            EmployeeEntity employee = employeeService.get(Long.parseLong(request.getParameter(ID_PARAM_NAME)));
            request.setAttribute(EMPLOYEE_PARAM_NAME, employee);
            dispatcher.forward(request, response);
        } else {
            request.setAttribute(PAGE_MODE_PARAM_NAME, CREATE_MODE);
            dispatcher.forward(request, response);
        }
    }

    private void createEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter(FIRST_NAME_PARAM_NAME);
        String lastName = request.getParameter(LAST_NAME_PARAM_NAME);
        String patronymic = request.getParameter(PATRONYMIC_PARAM_NAME);
        String position = request.getParameter(POSITION_PARAM_NAME);

        if (validateFields(request, firstName, lastName, patronymic, position)) {
            employeeService.add(new EmployeeEntity(firstName, lastName, patronymic, position));
            response.sendRedirect(COMMAND_EMPLOYEE_LIST);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE);
            dispatcher.forward(request, response);
        }
    }

    private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter(FIRST_NAME_PARAM_NAME);
        String lastName = request.getParameter(LAST_NAME_PARAM_NAME);
        String patronymic = request.getParameter(PATRONYMIC_PARAM_NAME);
        String position = request.getParameter(POSITION_PARAM_NAME);
        Long id = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        if (validateFields(request, firstName, lastName, patronymic, position)) {
            employeeService.update(new EmployeeEntity(firstName, lastName, patronymic, position, id));
            response.sendRedirect(COMMAND_EMPLOYEE_LIST);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE);
            dispatcher.forward(request, response);
        }
    }

    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long employeeId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        employeeService.delete(employeeId);
        response.sendRedirect(COMMAND_EMPLOYEE_LIST);
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
