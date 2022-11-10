package com.qulix.shilomy.trainingtask.web.controller.page.task;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodEmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodTaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
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

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по отображению страницы редактирования задачи.
 */
@WebServlet("/editTaskPage")
public class EditTaskPageController extends HttpServlet {
    private final ProjectService projectService = ProjectServiceImpl.getInstance(MethodProjectDao.getInstance());
    private final TaskService taskService = TaskServiceImpl.getInstance(MethodTaskDao.getInstance());
    private final EmployeeService employeeService = EmployeeServiceImpl.getInstance(MethodEmployeeDao.getInstance());

    /**
     * Метод обработки GET запроса, который добавляет на страницу необходимые для отображения параметры,
     * а затем перенаправляет на неё.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                  полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws IOException возникает в случае проблем с получением строки для перенаправления.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskEntity task = taskService.get(Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME)));
        request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME, ControllerConstants.EDIT_MODE);
        request.setAttribute(ControllerConstants.TASK_PARAM_NAME, task);

        request.setAttribute(ControllerConstants.EMPLOYEES_PARAM_NAME, employeeService.findAll());
        request.setAttribute(ControllerConstants.PROJECTS_PARAM_NAME, projectService.findAll());
        request.setAttribute(ControllerConstants.STATUS_PARAM_NAME, TaskStatus.values());
        if (request.getParameter(ControllerConstants.PROJECT_LOCK_PARAM) != null) {
            ProjectEntity currentProject = projectService.get(
                    Long.parseLong(request.getParameter(ControllerConstants.CURRENT_PROJECT_PARAM_NAME)));
            request.setAttribute(ControllerConstants.PROJECT_LOCK_PARAM, true);
            request.setAttribute(ControllerConstants.CURRENT_PROJECT_PARAM_NAME, currentProject);
        }
        request.getRequestDispatcher(ControllerConstants.EDIT_TASK_PAGE).forward(request, response);
    }
}
