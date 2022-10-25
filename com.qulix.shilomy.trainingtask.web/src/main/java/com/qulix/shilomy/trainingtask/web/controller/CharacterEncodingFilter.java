package com.qulix.shilomy.trainingtask.web.controller;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр кодировки символов с использованием UTF-8
 */
public class CharacterEncodingFilter implements Filter {

    /**
     * Метод инициализации
     * @param config a <code>FilterConfig</code> обЪект который хранит параметры
     *               конфигурации и инициализации фильтра
     */
    @Override
    public void init(FilterConfig config) {
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
     * Метод завершения
     */
    @Override
    public void destroy() {
    }
}
