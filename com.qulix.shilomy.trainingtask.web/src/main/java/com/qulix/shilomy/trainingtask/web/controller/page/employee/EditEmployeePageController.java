package com.qulix.shilomy.trainingtask.web.controller.page.employee;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;

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
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

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
        request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME, ControllerConstants.EDIT_MODE);
        EmployeeEntity employee = employeeService.get(Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME)));
        request.setAttribute(ControllerConstants.EMPLOYEE_PARAM_NAME, employee);
        request.getRequestDispatcher(ControllerConstants.EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
