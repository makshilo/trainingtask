package com.qulix.shilomy.trainingtask.web.controller.page.project;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Http сервлет для работы с проектами(ProjectEntity)
 */
@WebServlet("/projects")
public class ProjectListController extends HttpServlet {

    public static final String ID_PARAM_NAME = "id";
    public static final String PROJECTS_PARAM_NAME = "projects";

    public static final String PROJECT_LIST_PAGE = "/jsp/projectList.jsp";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);

    /**
     * Метод обработки GET запросов, получает действие из запроса и вызывает соответствующий метод
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                       полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                        отправляемый сервлетом клиенту
     *
     * @throws ServletException если в работе сервлета возникают проблемы
     * @throws IOException возникает в случае проблем с получением/записью данных
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(PROJECTS_PARAM_NAME, projectService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher(PROJECT_LIST_PAGE);
        dispatcher.forward(request, response);
    }
}
