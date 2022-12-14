package com.qulix.shilomy.trainingtask.web.controller.employee;

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
 * Сервлет обрабатывающий запросы страницы списка сотрудников
 */
@WebServlet("/employees")
public class EmployeeListController extends HttpServlet {
    private static final String EMPLOYEES_PAGE = "/jsp/employeeList.jsp";

    //Сервис работы с сотрудниками
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    /**
     * Обработка GET запроса по отображению списка сотрудников
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws ServletException ошибка сервлета при перенаправлении
     * @throws IOException      ошибка получения строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(EmployeeParam.EMPLOYEES.get(), employeeService.findAll());
        request.getRequestDispatcher(EMPLOYEES_PAGE).forward(request, response);
    }
}
