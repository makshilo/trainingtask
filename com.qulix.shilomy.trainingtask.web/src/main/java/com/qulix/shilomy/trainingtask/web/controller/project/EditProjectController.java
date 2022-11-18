package com.qulix.shilomy.trainingtask.web.controller.project;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeFormParams;
import com.qulix.shilomy.trainingtask.web.controller.task.TaskFormParams;
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
        ProjectEntity project = projectService.get(Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM.get())));
        request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME.get(), ControllerConstants.EDIT_MODE.get());
        request.setAttribute(ProjectFormParams.PROJECT_PARAM.get(), project);
        request.setAttribute(TaskFormParams.TASKS_PARAM.get(),
                TaskServiceImpl.getInstance(TaskDao.getInstance()).findByProject(project));
        request.setAttribute(EmployeeFormParams.EMPLOYEES_PARAM.get(), getEmployeeNames());
        request.getRequestDispatcher(ControllerConstants.EDIT_PROJECT_PAGE.get()).forward(request, response);
    }

    /**
     * Метод который создаёт таблицу, в которой ключ это идентификатор работника,
     * а значение полное имя
     * @return таблица идентификаторов и имён работников
     */
    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), employee.getLastName() + ControllerConstants.SPACE_SIGN.get() + employee.getFirstName());
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
            String projectName = request.getParameter(ProjectFormParams.PROJECT_NAME_PARAM.get());
            String description = request.getParameter(ProjectFormParams.DESCRIPTION_PARAM.get());
            Long projectId = Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM.get()));
            projectService.update(new ProjectEntity(projectName, description, projectId));
            response.sendRedirect(ControllerConstants.PROJECT_LIST.get());
        } else {
            request.setAttribute(ControllerConstants.ERROR_MESSAGES_PARAM.get(), errors);
            request.getRequestDispatcher(ControllerConstants.EDIT_PROJECT_PAGE.get()).forward(request, response);
        }
    }
}
