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

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по отображению страницы редактирования работника.
 */
@WebServlet("/editEmployeePage")
public class EditEmployeePageController extends HttpServlet {
    public static final String EMPLOYEE_PARAM_NAME = "employee";
    public static final String ID_PARAM_NAME = "id";
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String EDIT_MODE = "edit";

    private static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    /**
     * Метод обработки GET запроса,
     * который добавляет на страницу параметр режима формы и сущность для редактирования,
     * а затем перенаправляет на неё.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                  полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws IOException возникает в случае проблем с получением строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
        EmployeeEntity employee = employeeService.get(Long.parseLong(request.getParameter(ID_PARAM_NAME)));
        request.setAttribute(EMPLOYEE_PARAM_NAME, employee);
        request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
