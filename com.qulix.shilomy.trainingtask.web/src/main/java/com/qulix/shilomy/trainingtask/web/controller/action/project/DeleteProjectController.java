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
 * Класс HTTP сервлета, который отвечает за обработку запроса по удалению проекта.
 */
@WebServlet("/deleteProject")
public class DeleteProjectController extends HttpServlet {
    public static final String ID_PARAM_NAME = "id";

    private static final String COMMAND_PROJECT_LIST = "/projects";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);

    /**
     * Метод обработки POST запроса, который получает данные из запроса, удаляет сущность из базы,
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
        Long projectId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        projectService.delete(projectId);
        response.sendRedirect(COMMAND_PROJECT_LIST);
    }
}
