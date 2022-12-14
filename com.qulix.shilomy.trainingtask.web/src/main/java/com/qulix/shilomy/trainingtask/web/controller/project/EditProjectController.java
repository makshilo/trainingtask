package com.qulix.shilomy.trainingtask.web.controller.project;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
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

/**
 * Сервлет обрабатывающий запросы по редактированию проекта
 */
@WebServlet("/editProject")
public class EditProjectController extends HttpServlet {

    //Сервис работы с проектами
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    //Сервис работы с сотрудниками
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    /**
     * Обработка GET запроса перенаправления на форму проекта для редактирования
     *
     * @param request  {@link HttpServletRequest} запрос клиента
     * @param response {@link HttpServletResponse} ответ сервлета
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjectEntity project = projectService.get(Long.parseLong(request.getParameter(ProjectParam.ID.get())));
        request.setAttribute(ProjectParam.PAGE_MODE.get(), ProjectParam.EDIT.get());
        request.setAttribute(ProjectParam.PROJECT.get(), project);
        request.setAttribute(TaskParam.TASKS.get(), TaskServiceImpl.getInstance(TaskDao.getInstance()).findByProject(project));
        request.setAttribute(EmployeeParam.EMPLOYEES.get(), getEmployeeNames());
        request.getRequestDispatcher(ProjectParam.EDIT_PROJECT_PAGE.get()).forward(request, response);
    }

    /**
     * Создание таблицы идентификаторов и полных имён работников
     *
     * @return таблица соответствия идентификаторов и имён работников
     */
    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), String.join(" ", employee.getLastName(), employee.getFirstName()));
        }
        return employeeNames;
    }

    /**
     * Обработка POST запроса изменения проекта
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws IOException      ошибка получения строки для перенаправления
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (ProjectValidator.validate(request)) {
            String projectName = request.getParameter(ProjectParam.NAME.get());
            String description = request.getParameter(ProjectParam.DESCRIPTION.get());
            Long projectId = Long.parseLong(request.getParameter(ProjectParam.ID.get()));
            projectService.update(new ProjectEntity(projectName, description, projectId));
            response.sendRedirect(ProjectParam.PROJECT_LIST.get());
        } else {
            ProjectEntity project = projectService.get(Long.parseLong(request.getParameter(ProjectParam.ID.get())));
            request.setAttribute(TaskParam.TASKS.get(), TaskServiceImpl.getInstance(TaskDao.getInstance()).findByProject(project));
            request.setAttribute(EmployeeParam.EMPLOYEES.get(), getEmployeeNames());
            request.getRequestDispatcher(ProjectParam.EDIT_PROJECT_PAGE.get()).forward(request, response);
        }
    }
}
