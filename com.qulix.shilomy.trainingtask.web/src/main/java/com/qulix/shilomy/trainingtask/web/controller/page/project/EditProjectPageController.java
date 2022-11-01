package com.qulix.shilomy.trainingtask.web.controller.page.project;

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

@WebServlet("/editProjectPage")
public class EditProjectPageController extends HttpServlet {
    public static final String PROJECT_PARAM_NAME = "project";
    public static final String TASKS_PARAM_NAME = "tasks";
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String ID_PARAM_NAME = "id";

    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String EDIT_MODE = "edit";
    public static final String SPACE_SIGN = " ";


    public static final String EDIT_PROJECT_PAGE = "/jsp/editProject.jsp";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);
    private final TaskService taskService = (TaskService) serviceFactory.serviceFor(TaskEntity.class);
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjectEntity project = projectService.get(Long.parseLong(request.getParameter(ID_PARAM_NAME)));
        request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
        request.setAttribute(PROJECT_PARAM_NAME, project);
        request.setAttribute(TASKS_PARAM_NAME, taskService.findByProject(project));
        request.setAttribute(EMPLOYEES_PARAM_NAME, getEmployeeNames());
        request.getRequestDispatcher(EDIT_PROJECT_PAGE).forward(request, response);
    }

    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), employee.getLastName() + SPACE_SIGN + employee.getFirstName());
        }
        return employeeNames;
    }
}
