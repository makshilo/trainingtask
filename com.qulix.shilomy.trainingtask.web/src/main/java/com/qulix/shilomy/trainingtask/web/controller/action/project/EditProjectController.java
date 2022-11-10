package com.qulix.shilomy.trainingtask.web.controller.action.project;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по редактированию проекта.
 */
@WebServlet("/editProject")
public class EditProjectController extends HttpServlet {
    private final ProjectService projectService = ProjectServiceImpl.getInstance(MethodProjectDao.getInstance());

    /**
     * Метод обработки POST запроса, который получает данные из запроса, обновляет сущность в базе,
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
        String projectName = request.getParameter(ControllerConstants.PROJECT_NAME_PARAM);
        String description = request.getParameter(ControllerConstants.DESCRIPTION_PARAM_NAME);
        Long projectId = Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME));
        projectService.update(new ProjectEntity(projectName, description, projectId));
        response.sendRedirect(ControllerConstants.COMMAND_PROJECT_LIST);
    }
}
