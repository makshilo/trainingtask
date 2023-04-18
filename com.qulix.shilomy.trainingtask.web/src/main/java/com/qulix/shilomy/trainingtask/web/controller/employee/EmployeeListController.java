package com.qulix.shilomy.trainingtask.web.controller.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.EmployeeServiceImpl;

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
