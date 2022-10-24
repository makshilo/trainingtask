package com.qulix.shilomy.trainingtask.web.controller;

import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/projects")
public class ProjectController extends HttpServlet {

    public static final String ACTION_PARAM_NAME = "action";
    public static final String PROJECT_NAME_PARAM = "projectName";
    public static final String DESCRIPTION_PARAM_NAME = "description";
    public static final String ID_PARAM_NAME = "id";
    public static final String PROJECT_PARAM_NAME = "project";
    public static final String PROJECTS_PARAM_NAME = "projects";
    public static final String TASKS_PARAM_NAME = "tasks";
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String PROJECT_LIST_ACTION_NAME = "projectList";
    public static final String CREATE_PROJECT_PAGE_ACTION_NAME = "createProjectPage";
    public static final String CREATE_PROJECT_ACTION_NAME = "createProject";
    public static final String EDIT_PROJECT_PAGE_ACTION_NAME = "editProjectPage";
    public static final String EDIT_PROJECT_ACTION_NAME = "editProject";
    public static final String DELETE_PROJECT_ACTION_NAME = "deleteProject";
    public static final String CREATE_MODE = "create";
    public static final String EDIT_MODE = "edit";
    public static final String SPACE_SIGN = " ";
    public static final String EMPTY_STRING = "";

    public static final String PROJECT_IS_FOUND = "projectIsFound";
    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
    public static final String PROJECT_NAME_NULL = "projectNameNull";
    public static final String PROJECT_DESCRIPTION_NULL = "projectDescriptionNull";


    public static final String PROJECT_LIST_PAGE = "/jsp/projectList.jsp";
    public static final String EDIT_PROJECT_PAGE = "/jsp/editProject.jsp";
    private static final String COMMAND_PROJECT_LIST = "/projects?action=projectList";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);
    private final TaskService taskService = (TaskService) serviceFactory.serviceFor(TaskEntity.class);
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION_PARAM_NAME);

        switch (action) {
            case PROJECT_LIST_ACTION_NAME:
                projectList(request, response);
                break;
            case CREATE_PROJECT_PAGE_ACTION_NAME:
                showProjectForm(request, response, CREATE_MODE);
                break;
            case CREATE_PROJECT_ACTION_NAME:
                createProject(request, response);
                break;
            case EDIT_PROJECT_PAGE_ACTION_NAME:
                showProjectForm(request, response, EDIT_MODE);
                break;
            case EDIT_PROJECT_ACTION_NAME:
                editProject(request, response);
                break;
            case DELETE_PROJECT_ACTION_NAME:
                deleteProject(request, response);
                break;
        }
    }

    private void projectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(PROJECTS_PARAM_NAME, projectService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher(PROJECT_LIST_PAGE);
        dispatcher.forward(request, response);
    }

    private void showProjectForm(HttpServletRequest request, HttpServletResponse response, String pageMode) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_PROJECT_PAGE);
        if (pageMode.equals(EDIT_MODE)) {
            ProjectEntity project = projectService.get(Long.parseLong(request.getParameter(ID_PARAM_NAME)));
            request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
            request.setAttribute(PROJECT_PARAM_NAME, project);
            request.setAttribute(TASKS_PARAM_NAME, taskService.findByProject(project));
            request.setAttribute(EMPLOYEES_PARAM_NAME, getEmployeeNames());
            dispatcher.forward(request, response);
        } else {
            request.setAttribute(PAGE_MODE_PARAM_NAME, CREATE_MODE);
            dispatcher.forward(request, response);
        }
    }

    private void createProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String projectName = request.getParameter(PROJECT_NAME_PARAM);
        String description = request.getParameter(DESCRIPTION_PARAM_NAME);
        RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_PROJECT_PAGE);
        if (validateFields(request, projectName, description)) {
            ProjectEntity newProject = new ProjectEntity(projectName, description);
            if(!projectIsFound(projectService.findAll(), newProject)){
                projectService.add(newProject);
                response.sendRedirect(COMMAND_PROJECT_LIST);
            } else {
                request.setAttribute(VALIDATION_ERROR_PARAM_NAME, PROJECT_IS_FOUND);
                dispatcher.forward(request, response);
            }
        } else {
            dispatcher.forward(request, response);
        }
    }

    private void editProject(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String projectName = request.getParameter(PROJECT_NAME_PARAM);
        String description = request.getParameter(DESCRIPTION_PARAM_NAME);
        Long projectId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        if (validateFields(request, projectName, description)) {
            projectService.update(new ProjectEntity(projectName, description, projectId));
            response.sendRedirect(COMMAND_PROJECT_LIST);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_PROJECT_PAGE);
            dispatcher.forward(request, response);
        }
    }

    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long projectId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        projectService.delete(projectId);
        response.sendRedirect(COMMAND_PROJECT_LIST);
    }

    private boolean projectIsFound(List<ProjectEntity> projects, ProjectEntity newProject) {
        for (ProjectEntity project: projects) {
            if(project.getName().equals(newProject.getName()) && project.getDescription().equals(newProject.getDescription())) {
                return true;
            }
        }
        return false;
    }

    private boolean validateFields(HttpServletRequest request, String projectName, String projectDescription) {
        if (projectName == null || projectName.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, PROJECT_NAME_NULL);
            return false;
        }
        if (projectDescription == null || projectDescription.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, PROJECT_DESCRIPTION_NULL);
            return false;
        }
        return true;
    }

    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), employee.getLastName() + SPACE_SIGN + employee.getFirstName());
        }
        return employeeNames;
    }
}
