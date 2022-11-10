package com.qulix.shilomy.trainingtask.web.controller.page.project;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.MethodProjectDao;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;

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
    private final ProjectService projectService = ProjectServiceImpl.getInstance(MethodProjectDao.getInstance());

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
        request.setAttribute(ControllerConstants.PROJECTS_PARAM_NAME, projectService.findAll());
        request.getRequestDispatcher(ControllerConstants.PROJECT_LIST_PAGE).forward(request, response);
    }
}
