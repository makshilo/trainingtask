package com.qulix.shilomy.trainingtask.web.controller.action.project;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.web.validator.ProjectValidator;

import javax.servlet.ServletException;
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
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

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
     * @throws ServletException если в работе сервлета возникают проблемы при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (ProjectValidator.isValid(request)) {
            String projectName = request.getParameter(ControllerConstants.PROJECT_NAME_PARAM);
            String description = request.getParameter(ControllerConstants.DESCRIPTION_PARAM_NAME);
            Long projectId = Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME));
            projectService.update(new ProjectEntity(projectName, description, projectId));
            response.sendRedirect(ControllerConstants.COMMAND_PROJECT_LIST);
        } else {
            request.getRequestDispatcher(ControllerConstants.EDIT_PROJECT_PAGE).forward(request, response);
        }
    }
}
