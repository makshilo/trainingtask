package com.qulix.shilomy.trainingtask.web.controller.project;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.web.validator.ProjectValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по редактированию проекта.
 */
@WebServlet("/editProject")
public class EditProjectController extends HttpServlet {
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());
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
        ProjectEntity project = projectService.get(Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME)));
        request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME, ControllerConstants.EDIT_MODE);
        request.setAttribute(ControllerConstants.PROJECT_PARAM_NAME, project);
        request.setAttribute(ControllerConstants.TASKS_PARAM_NAME,
                TaskServiceImpl.getInstance(TaskDao.getInstance()).findByProject(project));
        request.setAttribute(ControllerConstants.EMPLOYEES_PARAM_NAME, getEmployeeNames());
        request.getRequestDispatcher(ControllerConstants.EDIT_PROJECT_PAGE).forward(request, response);
    }

    /**
     * Метод который создаёт таблицу, в которой ключ это идентификатор работника,
     * а значение полное имя
     * @return таблица идентификаторов и имён работников
     */
    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), employee.getLastName() + ControllerConstants.SPACE_SIGN + employee.getFirstName());
        }
        return employeeNames;
    }

    /**
     * Метод обработки POST запроса, который получает данные из запроса, обновляет сущность в базе,
     * а потом перенаправляет на страницу со списком проектов.
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
        Map<String, String> errors = ProjectValidator.isValid(request);
        if (errors.isEmpty()) {
            String projectName = request.getParameter(ControllerConstants.PROJECT_NAME_PARAM);
            String description = request.getParameter(ControllerConstants.DESCRIPTION_PARAM_NAME);
            Long projectId = Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME));
            projectService.update(new ProjectEntity(projectName, description, projectId));
            response.sendRedirect(ControllerConstants.COMMAND_PROJECT_LIST);
        } else {
            request.setAttribute(ControllerConstants.ERROR_MESSAGES_PARAM, errors);
            request.getRequestDispatcher(ControllerConstants.EDIT_PROJECT_PAGE).forward(request, response);
        }
    }
}
