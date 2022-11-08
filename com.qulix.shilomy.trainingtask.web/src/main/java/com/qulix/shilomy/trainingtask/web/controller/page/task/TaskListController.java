package com.qulix.shilomy.trainingtask.web.controller.page.task;

import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

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
    public static final String TASKS_PARAM_NAME = "tasks";
    public static final String ID_PARAM_NAME = "id";
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String PROJECTS_PARAM_NAME = "projects";

    private static final String TASK_LIST_PAGE = "/jsp/taskList.jsp";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);
    private final TaskService taskService = (TaskService) serviceFactory.serviceFor(TaskEntity.class);
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

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
        request.setAttribute(TASKS_PARAM_NAME, taskService.findAll());
        request.setAttribute(EMPLOYEES_PARAM_NAME, getEmployeeNames());
        request.setAttribute(PROJECTS_PARAM_NAME, getProjectNames());
        request.getRequestDispatcher(TASK_LIST_PAGE).forward(request, response);
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
