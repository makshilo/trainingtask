package com.qulix.shilomy.trainingtask.web.controller.action.project;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/createProject")
public class CreateProjectController extends HttpServlet {
    public static final String PROJECT_NAME_PARAM = "projectName";
    public static final String DESCRIPTION_PARAM_NAME = "description";
    public static final String EMPTY_STRING = "";

    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
    public static final String PROJECT_IS_FOUND = "Такой проект уже существует";
    public static final String PROJECT_NAME_NULL = "Наименование проекта не заполнено";
    public static final String PROJECT_DESCRIPTION_NULL = "Описание проекта не заполнено";

    public static final String EDIT_PROJECT_PAGE = "/jsp/editProject.jsp";
    private static final String COMMAND_PROJECT_LIST = "/projects";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
