package com.qulix.shilomy.trainingtask.web.controller.filter;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Фильтр проверки данных формы проекта.
 */
@WebFilter(filterName = "ProjectValidationFilter", urlPatterns = {"/createProject", "/editProject"})
public class ProjectValidationFilter implements Filter {
    private static final String PROJECT_NAME_NULL = "Наименование проекта не заполнено";
    private static final String PROJECT_DESCRIPTION_NULL = "Описание проекта не заполнено";
    public static final String EDIT_PROJECT = "/editProject";

    /**
     * Метод инициализации
     * @param filterConfig a <code>FilterConfig</code> обЪект который хранит параметры
     *               конфигурации и инициализации фильтра
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Метод который получает данные из запроса и проверяет их корректность,
     * если данные некорректны возвращает на страницу формы,
     * если данные корректны передаёт запрос следующему фильтру в цепочке.
     * @param request <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @param response <code>ServletResponse</code> объект который хранит ответ фильтра
     * @param chain <code>FilterChain</code> для вызова следующего ресурса фильтра
     * @throws IOException возникает в случае проблем с получением строки для перенаправления
     * @throws ServletException если в работе сервлета возникают проблемы
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String page = ((HttpServletRequest)request).getRequestURI();
        if (page.equals(EDIT_PROJECT)) {
            request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME, ControllerConstants.EDIT_MODE);
        }

        String projectName = request.getParameter(ControllerConstants.PROJECT_NAME_PARAM);
        String description = request.getParameter(ControllerConstants.DESCRIPTION_PARAM_NAME);

        if (projectName == null || projectName.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, PROJECT_NAME_NULL);
            request.getRequestDispatcher(ControllerConstants.EDIT_PROJECT_PAGE).forward(request, response);
        } else if (description == null || description.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, PROJECT_DESCRIPTION_NULL);
            request.getRequestDispatcher(ControllerConstants.EDIT_PROJECT_PAGE).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Метод завершения работы фильтра
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
