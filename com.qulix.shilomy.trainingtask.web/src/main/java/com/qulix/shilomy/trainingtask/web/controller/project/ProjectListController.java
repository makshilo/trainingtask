package com.qulix.shilomy.trainingtask.web.controller.project;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws ServletException если в работе сервлета возникают проблемы.
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ControllerConstant.PROJECTS_PARAM.get(), projectService.findAll());
        request.getRequestDispatcher(PROJECT_LIST_PAGE).forward(request, response);
    }
}
