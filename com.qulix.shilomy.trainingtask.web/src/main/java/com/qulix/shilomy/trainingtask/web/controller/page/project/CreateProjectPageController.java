package com.qulix.shilomy.trainingtask.web.controller.page.project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createProjectPage")
public class CreateProjectPageController extends HttpServlet {
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String CREATE_MODE = "create";

    public static final String EDIT_PROJECT_PAGE = "/jsp/editProject.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_PROJECT_PAGE);
        request.setAttribute(PAGE_MODE_PARAM_NAME, CREATE_MODE);
        dispatcher.forward(request, response);
    }
}
