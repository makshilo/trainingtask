package com.qulix.shilomy.trainingtask.web.controller.task;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Сервлет обрабатывающий запросы страницы списка задач
 */
@WebServlet("/tasks")
public class TaskListController extends HttpServlet {
    private static final String TASK_LIST_PAGE = "/jsp/taskList.jsp";

    //Сервис работы с проектами
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    //Сервис работы с сотрудниками
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    //Сервис работы с задачами
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    /**
     * Обработка GET запроса по отображению списка задач
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws ServletException если в работе сервлета возникают проблемы.
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ControllerConstant.TASKS_PARAM.get(), taskService.findAll());
        request.setAttribute(ControllerConstant.EMPLOYEES_PARAM.get(), getEmployeeNames());
        request.setAttribute(ControllerConstant.PROJECTS_PARAM.get(), getProjectNames());
        request.setAttribute(TaskFormParam.STATUS_PARAM.get(), TaskStatus.values());
        request.getRequestDispatcher(TASK_LIST_PAGE).forward(request, response);
    }

    /**
     * Создание таблицы идентификаторов и полных имён сотрудников
     * @return таблица соответствия идентификаторов и имён сотрудников
     */
    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), String.join(ControllerConstant.SPACE_SIGN.get(),
                    employee.getLastName(), employee.getFirstName()));
        }
        return employeeNames;
    }

    /**
     * Создание таблицы идентификаторов и имён проектов
     * @return таблица идентификаторов и имён проектов
     */
    private HashMap<Long, String> getProjectNames() {
        final HashMap<Long, String> projectNames = new HashMap<>();
        for (ProjectEntity project : projectService.findAll()) {
            projectNames.put(project.getId(), project.getName());
        }
        return projectNames;
    }
}
