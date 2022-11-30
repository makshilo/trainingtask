package com.qulix.shilomy.trainingtask.web.controller.project;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
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
 * Сервлет обрабатывающий запросы по созданию проекта
 */
@WebServlet("/createProject")
public class CreateProjectController extends HttpServlet {

    //Сервис работы с проектами
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    /**
     * Обработка GET запроса перенаправления на форму проекта
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ControllerConstant.PAGE_MODE_PARAM_NAME.get(), ControllerConstant.CREATE_MODE.get());
        request.getRequestDispatcher(ControllerConstant.EDIT_PROJECT_PAGE.get()).forward(request, response);
    }

    /**
     * Обработка POST запроса создания проекта
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошибка получения строки для перенаправления
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (ProjectValidator.validate(request)){
            String projectName = request.getParameter(ProjectFormParam.PROJECT_NAME_PARAM.get());
            String description = request.getParameter(ProjectFormParam.DESCRIPTION_PARAM.get());
            ProjectEntity newProject = new ProjectEntity(projectName, description);
            projectService.add(newProject);
            response.sendRedirect(ControllerConstant.PROJECT_LIST.get());
        } else {
            request.getRequestDispatcher(ControllerConstant.EDIT_PROJECT_PAGE.get()).forward(request, response);
        }
    }
}
