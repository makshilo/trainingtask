package com.qulix.shilomy.trainingtask.web.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Фильтр кодировки символов с использованием UTF-8
 */
@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {

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
     * Метод фильтрации символов
     * @param request <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @param response <code>ServletResponse</code> объект который хранит ответ фильтра
     * @param chain <code>FilterChain</code> для вызова следующего ресурса фильтра
     * @throws IOException возникает в случае проблем с получением/записью данных
     * @throws ServletException если в работе сервлета возникают проблемы
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    /**
     * Метод завершения работы фильтра
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
