package com.qulix.shilomy.trainingtask.web.controller.page.task;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodEmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodTaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.TaskService;
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
 * HTTP сервлет для отображения страницы списка проектов.
 */
@WebServlet("/tasks")
public class TaskListController extends HttpServlet {
    private final ProjectService projectService = ProjectServiceImpl.getInstance(MethodProjectDao.getInstance());
    private final TaskService taskService = TaskServiceImpl.getInstance(MethodTaskDao.getInstance());
    private final EmployeeService employeeService = EmployeeServiceImpl.getInstance(MethodEmployeeDao.getInstance());

    /**
     * Метод обработки GET запросов, который добавляет на страницу необходимые для её работы данные,
     * а затем перенаправляет на неё.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                       полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                        отправляемый сервлетом клиенту
     *
     * @throws ServletException если в работе сервлета возникают проблемы.
     * @throws IOException возникает в случае проблем с получением строки для перенаправления.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ControllerConstants.TASKS_PARAM_NAME, taskService.findAll());
        request.setAttribute(ControllerConstants.EMPLOYEES_PARAM_NAME, getEmployeeNames());
        request.setAttribute(ControllerConstants.PROJECTS_PARAM_NAME, getProjectNames());
        request.getRequestDispatcher(ControllerConstants.TASK_LIST_PAGE).forward(request, response);
    }

    /**
     * Метод который создаёт таблицу, в которой ключ это идентификатор работника,
     * а значение полное имя
     * @return таблица идентификаторов и имён работников
     */
    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), employee.getLastName() + " " + employee.getFirstName());
        }
        return employeeNames;
    }

    /**
     * Метод который создаёт таблицу, в которой ключ это идентификатор проекта,
     * а значение наименование проекта
     * @return таблица идентификаторов и наименований проектов
     */
    private HashMap<Long, String> getProjectNames() {
        final HashMap<Long, String> projectNames = new HashMap<>();
        for (ProjectEntity project : projectService.findAll()) {
            projectNames.put(project.getId(), project.getName());
        }
        return projectNames;
    }
}
