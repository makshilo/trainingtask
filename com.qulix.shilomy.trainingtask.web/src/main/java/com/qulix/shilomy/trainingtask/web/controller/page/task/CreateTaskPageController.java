package com.qulix.shilomy.trainingtask.web.controller.page.task;

import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createTaskPage")
public class CreateTaskPageController extends HttpServlet {
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String PROJECTS_PARAM_NAME = "projects";
    public static final String CURRENT_PROJECT_PARAM_NAME = "currentProject";
    public static final String PROJECT_LOCK_PARAM = "projectLock";
    public static final String TASK_STATUS_PARAM_NAME = "taskStatus";
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String CREATE_MODE = "create";

    private static final String EDIT_TASK_PAGE = "/jsp/editTask.jsp";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(PAGE_MODE_PARAM_NAME, CREATE_MODE);
        request.setAttribute(EMPLOYEES_PARAM_NAME, employeeService.findAll());
        request.setAttribute(PROJECTS_PARAM_NAME, projectService.findAll());
        request.setAttribute(TASK_STATUS_PARAM_NAME, TaskStatus.values());
        if (request.getParameter(PROJECT_LOCK_PARAM) != null) {
            ProjectEntity currentProject = projectService.get(Long.parseLong(request.getParameter(CURRENT_PROJECT_PARAM_NAME)));
            request.setAttribute(PROJECT_LOCK_PARAM, true);
            request.setAttribute(CURRENT_PROJECT_PARAM_NAME, currentProject);
        }
        request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
    }
}
