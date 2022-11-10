package com.qulix.shilomy.trainingtask.web.controller.action.employee;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по созданию работника
 */
@WebServlet("/createEmployee")
public class CreateEmployeeController extends HttpServlet {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    /**
     * Метод обработки POST запроса, который получает данные из запроса, добавляет новую сущность в базу,
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
        String firstName = request.getParameter(ControllerConstants.FIRST_NAME_PARAM_NAME);
        String lastName = request.getParameter(ControllerConstants.LAST_NAME_PARAM_NAME);
        String patronymic = request.getParameter(ControllerConstants.PATRONYMIC_PARAM_NAME);
        String position = request.getParameter(ControllerConstants.POSITION_PARAM_NAME);

        employeeService.add(new EmployeeEntity(firstName, lastName, patronymic, position));
        response.sendRedirect(ControllerConstants.COMMAND_EMPLOYEE_LIST);
    }
}
