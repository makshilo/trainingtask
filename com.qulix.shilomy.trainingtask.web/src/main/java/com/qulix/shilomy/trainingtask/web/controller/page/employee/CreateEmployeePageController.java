package com.qulix.shilomy.trainingtask.web.controller.page.employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по отображению страницы создания работника.
 */
@WebServlet("/createEmployeePage")
public class CreateEmployeePageController extends HttpServlet {
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String CREATE_MODE = "create";

    private static final String EDIT_EMPLOYEE_PAGE = "/jsp/editEmployee.jsp";

    /**
     * Метод обработки GET запроса, который добавляет на страницу параметр режима формы,
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
        request.setAttribute(PAGE_MODE_PARAM_NAME, CREATE_MODE);
        request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
