package com.qulix.shilomy.trainingtask.web.controller.employee;

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
 * Сервлет обрабатывающий запросы по редактированию сотрудника
 */
@WebServlet("/editEmployee")
public class EditEmployeeController extends HttpServlet {

    //Сервис работы с сотрудниками
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    /**
     * Обработка GET запроса перенаправления на форму сотрудника для редактирования
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(EmployeeParam.PAGE_MODE.get(), EmployeeParam.EDIT.get());
        EmployeeEntity employee = employeeService.get(Long.parseLong(request.getParameter(EmployeeParam.ID.get())));
        request.setAttribute(EmployeeParam.EMPLOYEE.get(), employee);
        request.getRequestDispatcher(EmployeeParam.EDIT_EMPLOYEE_PAGE.get()).forward(request, response);
    }

    /**
     * Обработка POST запроса изменения сотрудника
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошубка получениея строки для перенаправления
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (EmployeeValidator.isValid(request)) {
            String firstName = request.getParameter(EmployeeParam.FIRST_NAME.get());
            String lastName = request.getParameter(EmployeeParam.LAST_NAME.get());
            String patronymic = request.getParameter(EmployeeParam.PATRONYMIC.get());
            String position = request.getParameter(EmployeeParam.POSITION.get());
            Long id = Long.parseLong(request.getParameter(EmployeeParam.ID.get()));

            employeeService.update(new EmployeeEntity(firstName, lastName, patronymic, position, id));
            response.sendRedirect(EmployeeParam.EMPLOYEE_LIST.get());
        } else {
            request.getRequestDispatcher(EmployeeParam.EDIT_EMPLOYEE_PAGE.get()).forward(request, response);
        }
    }
}
