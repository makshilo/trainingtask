package com.qulix.shilomy.trainingtask.web.controller.page.project;

import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HTTP сервлет для отображения страницы списка проектов.
 */
@WebServlet("/projects")
public class ProjectListController extends HttpServlet {

    public static final String ID_PARAM_NAME = "id";
    public static final String PROJECTS_PARAM_NAME = "projects";

    public static final String PROJECT_LIST_PAGE = "/jsp/projectList.jsp";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);

    /**
     * Метод обработки GET запросов, который добавляет на страницу необходимые для её работы данные,
     * а затем перенаправляет на неё.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                       полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                        отправляемый сервлетом клиенту
     *
     * @throws ServletException если в работе сервлета возникают проблемы.
     * @throws IOException возникает в случае проблем с получением строки для перенаправления.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(PROJECTS_PARAM_NAME, projectService.findAll());
        request.getRequestDispatcher(PROJECT_LIST_PAGE).forward(request, response);
    }
}