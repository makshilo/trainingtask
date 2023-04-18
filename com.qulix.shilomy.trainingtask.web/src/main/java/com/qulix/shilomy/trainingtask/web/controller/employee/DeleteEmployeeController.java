package com.qulix.shilomy.trainingtask.web.controller.employee;


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
 * Сервлет обрабатывающий запросы по удалению сотрудника
 */
@WebServlet("/deleteEmployee")
public class DeleteEmployeeController extends HttpServlet {

    //Сервис работы с сотрудниками
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    /**
     * Обработка POST запроса по удалению сотрудника
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long employeeId = Long.parseLong(request.getParameter(EmployeeParam.ID.get()));
        employeeService.delete(employeeId);
        response.sendRedirect(EmployeeParam.EMPLOYEE_LIST.get());
    }
}
