package com.qulix.shilomy.trainingtask.web.controller.project;

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
import java.util.Map;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по созданию проекта.
 */
@WebServlet("/createProject")
public class CreateProjectController extends HttpServlet {

    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    /**
     * Метод обработки GET запроса, который добавляет на страницу параметр режима формы,
     * а затем перенаправляет на неё.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                  полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws IOException возникает в случае проблем с получением строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME.get(), ControllerConstants.CREATE_MODE.get());
        request.getRequestDispatcher(ControllerConstants.EDIT_PROJECT_PAGE.get()).forward(request, response);
    }

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
     * @throws ServletException если в работе сервлета возникают проблемы при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> errors = ProjectValidator.isValid(request);
        if (errors.isEmpty()){
            String projectName = request.getParameter(ProjectFormParams.PROJECT_NAME_PARAM.get());
            String description = request.getParameter(ProjectFormParams.DESCRIPTION_PARAM.get());
            ProjectEntity newProject = new ProjectEntity(projectName, description);
            projectService.add(newProject);
            response.sendRedirect(ControllerConstants.PROJECT_LIST.get());
        } else {
            request.setAttribute(ControllerConstants.ERROR_MESSAGES_PARAM.get(), errors);
            request.getRequestDispatcher(ControllerConstants.EDIT_PROJECT_PAGE.get()).forward(request, response);
        }
    }
}
