package com.qulix.shilomy.trainingtask.web.controller.project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.qulix.shilomy.trainingtask.data.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.param.ProjectParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.ProjectServiceImpl;

/**
 * Сервлет обрабатывающий запросы страницы списка проектов
 */
@WebServlet("/projects")
public class ProjectListController extends HttpServlet {
    private static final String PROJECT_LIST_PAGE = "/jsp/projectList.jsp";

    //Сервис для работы с проектами
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    /**
     * Обработка GET запроса по отображению списка проектов
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws ServletException если в работе сервлета возникают проблемы.
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ProjectParam.PROJECTS.get(), projectService.findAll());
        request.getRequestDispatcher(PROJECT_LIST_PAGE).forward(request, response);
    }
}
