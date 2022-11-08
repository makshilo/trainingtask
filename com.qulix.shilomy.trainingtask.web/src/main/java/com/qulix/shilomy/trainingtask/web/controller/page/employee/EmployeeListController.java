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
 * HTTP сервлет для отображения страницы списка работников.
 */
@WebServlet("/employees")
public class EmployeeListController extends HttpServlet {
    public static final String EMPLOYEES_PARAM_NAME = "employees";

    private static final String EMPLOYEES_PAGE = "/jsp/employeeList.jsp";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    /**
     * Метод обработки GET запросов, который добавляет на страницу необходимые для её работы данные,
     * а затем перенаправляет на неё.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                       полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                        отправляемый сервлетом клиенту
     *
     * @throws ServletException если в работе сервлета возникают проблемы.
     * @throws IOException возникает в случае проблем с получением строки для перенаправления.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(EMPLOYEES_PARAM_NAME, employeeService.findAll());
        request.getRequestDispatcher(EMPLOYEES_PAGE).forward(request, response);
    }
}
