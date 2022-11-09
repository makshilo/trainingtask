package com.qulix.shilomy.trainingtask.web.controller.action.project;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по созданию проекта.
 */
@WebServlet("/createProject")
public class CreateProjectController extends HttpServlet {
    public static final String PROJECT_NAME_PARAM = "projectName";
    public static final String DESCRIPTION_PARAM_NAME = "description";

    private static final String COMMAND_PROJECT_LIST = "/projects";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);

    /**
     * Метод обработки POST запроса, который получает данные из запроса, добавляет новую сущность в базу,
     * а потом перенаправляет на страницу со списком проектов.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                  полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws IOException возникает в случае проблем с получением строки для перенаправления
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String projectName = request.getParameter(PROJECT_NAME_PARAM);
        String description = request.getParameter(DESCRIPTION_PARAM_NAME);
        ProjectEntity newProject = new ProjectEntity(projectName, description);
        projectService.add(newProject);
        response.sendRedirect(COMMAND_PROJECT_LIST);
    }
}