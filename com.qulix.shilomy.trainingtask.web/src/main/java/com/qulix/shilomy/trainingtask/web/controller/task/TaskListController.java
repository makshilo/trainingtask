package com.qulix.shilomy.trainingtask.web.controller.task;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.data.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.data.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.data.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.data.param.ProjectParam;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws ServletException если в работе сервлета возникают проблемы.
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(TaskParam.TASKS.get(), taskService.findAll());
        request.setAttribute(EmployeeParam.EMPLOYEES.get(), getEmployeeNames());
        request.setAttribute(ProjectParam.PROJECTS.get(), getProjectNames());
        request.setAttribute(TaskParam.STATUS.get(), TaskStatus.values());
        request.getRequestDispatcher(TASK_LIST_PAGE).forward(request, response);
    }

    /**
     * Создание таблицы идентификаторов и полных имён сотрудников
     *
     * @return таблица соответствия идентификаторов и имён сотрудников
     */
    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), String.join(" ", employee.getLastName(), employee.getFirstName()));
        }
        return employeeNames;
    }

    /**
     * Создание таблицы идентификаторов и имён проектов
     *
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
