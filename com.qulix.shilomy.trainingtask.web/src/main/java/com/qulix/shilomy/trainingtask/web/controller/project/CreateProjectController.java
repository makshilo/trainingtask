package com.qulix.shilomy.trainingtask.web.controller.project;

import com.qulix.shilomy.trainingtask.data.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.param.ProjectParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.web.validator.ProjectValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ProjectParam.PAGE_MODE.get(), ProjectParam.CREATE.get());
        request.getRequestDispatcher(ProjectParam.EDIT_PROJECT_PAGE.get()).forward(request, response);
    }

    /**
     * Обработка POST запроса создания проекта
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws IOException      ошибка получения строки для перенаправления
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (ProjectValidator.validate(request)) {
            String projectName = request.getParameter(ProjectParam.NAME.get());
            String description = request.getParameter(ProjectParam.DESCRIPTION.get());
            ProjectEntity newProject = new ProjectEntity(projectName, description);
            projectService.add(newProject);
            response.sendRedirect(ProjectParam.PROJECT_LIST.get());
        } else {
            request.getRequestDispatcher(ProjectParam.EDIT_PROJECT_PAGE.get()).forward(request, response);
        }
    }
}
