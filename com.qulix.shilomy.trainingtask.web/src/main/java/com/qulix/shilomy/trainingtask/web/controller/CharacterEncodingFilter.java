package com.qulix.shilomy.trainingtask.web.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Фильтр кодировки символов
 */
@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {

    /**
     * Инициализация
     *
     * @param filterConfig a <code>FilterConfig</code> объект который хранит параметры фильтра
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Фильтрация символов
     *
     * @param request  <code>ServletRequest</code> запрос
     * @param response <code>ServletResponse</code> ответ
     * @param chain    <code>FilterChain</code> цепочка фильтров
     * @throws IOException      возникает в случае проблем с получением/записью данных
     * @throws ServletException если в работе фильтра возникают проблемы
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    /**
     * Завершение работы фильтра
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
