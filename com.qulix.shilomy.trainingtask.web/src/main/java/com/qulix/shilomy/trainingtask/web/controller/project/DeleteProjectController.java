package com.qulix.shilomy.trainingtask.web.controller.project;

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
 * Сервлет обрабатывающий запросы по удалению проекта
 */
@WebServlet("/deleteProject")
public class DeleteProjectController extends HttpServlet {
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    /**
     * Обработка POST запроса по удалению проекта
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long projectId = Long.parseLong(request.getParameter(ProjectParam.ID.get()));
        projectService.delete(projectId);
        response.sendRedirect(ProjectParam.PROJECT_LIST.get());
    }
}
