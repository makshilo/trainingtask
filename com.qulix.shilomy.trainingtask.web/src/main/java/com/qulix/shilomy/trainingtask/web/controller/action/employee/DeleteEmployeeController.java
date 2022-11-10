package com.qulix.shilomy.trainingtask.web.controller.action.employee;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodEmployeeDao;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по удалению работника.
 */
@WebServlet("/deleteEmployee")
public class DeleteEmployeeController extends HttpServlet {
    private final EmployeeService employeeService = EmployeeServiceImpl.getInstance(MethodEmployeeDao.getInstance());

    private static final String COMMAND_EMPLOYEE_LIST = "/employees";

    /**
     * Метод обработки POST запроса, который получает данные из запроса, удаляет сущность из базы,
     * а потом перенаправляет на страницу со списком сотрудников.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                  полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws IOException возникает в случае проблем с получением строки для перенаправления
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long employeeId = Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME));
        employeeService.delete(employeeId);
        response.sendRedirect(COMMAND_EMPLOYEE_LIST);
    }
}
