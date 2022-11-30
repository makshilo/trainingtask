package com.qulix.shilomy.trainingtask.web.controller.employee;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
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
 * Сервлет обрабатывающий запросы по созданию работника
 */
@WebServlet("/createEmployee")
public class CreateEmployeeController extends HttpServlet {

    //Сервис работы с работниками
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    /**
     * Обработка GET запроса по перенаправлению на форму работника
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ControllerConstant.PAGE_MODE_PARAM_NAME.get(), ControllerConstant.CREATE_MODE.get());
        request.getRequestDispatcher(ControllerConstant.EDIT_EMPLOYEE_PAGE.get()).forward(request, response);
    }

    /**
     * Обработка POST запроса по добавлению работника
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException проблема с получением строки для перенаправления
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (EmployeeValidator.isValid(request)) {
            String firstName = request.getParameter(EmployeeFormParam.EMPLOYEE_FIRST_NAME.get());
            String lastName = request.getParameter(EmployeeFormParam.EMPLOYEE_LAST_NAME.get());
            String patronymic = request.getParameter(EmployeeFormParam.PATRONYMIC_PARAM.get());
            String position = request.getParameter(EmployeeFormParam.POSITION_PARAM.get());

            employeeService.add(new EmployeeEntity(firstName, lastName, patronymic, position));
            response.sendRedirect(ControllerConstant.EMPLOYEE_LIST.get());
        } else {
            request.getRequestDispatcher(ControllerConstant.EDIT_EMPLOYEE_PAGE.get()).forward(request, response);
        }
    }
}
