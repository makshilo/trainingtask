package com.qulix.shilomy.trainingtask.web.controller.action.employee;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.web.validator.EmployeeValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по редактированию работника.
 */
@WebServlet("/editEmployee")
public class EditEmployeeController extends HttpServlet {
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    /**
     * Метод обработки POST запроса, который получает данные из запроса, обновляет сущность в базе,
     * а потом перенаправляет на страницу со списком сотрудников.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                  полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws IOException возникает в случае проблем с получением строки для перенаправления
     * @throws ServletException если в работе сервлета возникают проблемы при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (EmployeeValidator.isValid(request)) {
            String firstName = request.getParameter(ControllerConstants.FIRST_NAME_PARAM_NAME);
            String lastName = request.getParameter(ControllerConstants.LAST_NAME_PARAM_NAME);
            String patronymic = request.getParameter(ControllerConstants.PATRONYMIC_PARAM_NAME);
            String position = request.getParameter(ControllerConstants.POSITION_PARAM_NAME);
            Long id = Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME));

            employeeService.update(new EmployeeEntity(firstName, lastName, patronymic, position, id));
            response.sendRedirect(ControllerConstants.COMMAND_EMPLOYEE_LIST);
        } else {
            request.getRequestDispatcher(ControllerConstants.EDIT_EMPLOYEE_PAGE).forward(request, response);
        }
    }
}
