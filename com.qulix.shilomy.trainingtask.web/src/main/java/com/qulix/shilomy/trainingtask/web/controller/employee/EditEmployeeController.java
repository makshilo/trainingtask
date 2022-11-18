package com.qulix.shilomy.trainingtask.web.controller.employee;

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
import java.util.Map;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по редактированию работника.
 */
@WebServlet("/editEmployee")
public class EditEmployeeController extends HttpServlet {
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
        request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME.get(), ControllerConstants.EDIT_MODE.get());
        EmployeeEntity employee = employeeService.get(Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM.get())));
        request.setAttribute(EmployeeFormParams.EMPLOYEE_PARAM.get(), employee);
        request.getRequestDispatcher(ControllerConstants.EDIT_EMPLOYEE_PAGE.get()).forward(request, response);
    }

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
        Map<String, String> errors = EmployeeValidator.isValid(request);
        if (errors.isEmpty()) {
            String firstName = request.getParameter(EmployeeFormParams.EMPLOYEE_FIRST_NAME.get());
            String lastName = request.getParameter(EmployeeFormParams.EMPLOYEE_LAST_NAME.get());
            String patronymic = request.getParameter(EmployeeFormParams.PATRONYMIC_PARAM.get());
            String position = request.getParameter(EmployeeFormParams.POSITION_PARAM.get());
            Long id = Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM.get()));

            employeeService.update(new EmployeeEntity(firstName, lastName, patronymic, position, id));
            response.sendRedirect(ControllerConstants.EMPLOYEE_LIST.get());
        } else {
            request.setAttribute(ControllerConstants.ERROR_MESSAGES_PARAM.get(), errors);
            request.getRequestDispatcher(ControllerConstants.EDIT_EMPLOYEE_PAGE.get()).forward(request, response);
        }
    }
}
