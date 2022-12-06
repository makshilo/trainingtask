package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.PageConstant;
import com.qulix.shilomy.trainingtask.web.controller.project.ProjectParam;
import com.qulix.shilomy.trainingtask.web.validator.composite.ProjectComposite;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Валидатор параметров проекта
 */
public class ProjectValidator {

    public static final String EDIT_PROJECT = "/editProject";

    private ProjectValidator() {

    }

    /**
     * Проверка параметров и добавление ошибок на страницу
     * @param req <code>ServletRequest</code> запрос пользователя
     * @return true когда все параметры корректны, в остальных случаях false
     */
    public static boolean validate(HttpServletRequest req) {
        String page = req.getRequestURI();
        if (page.equals(EDIT_PROJECT)) {
            req.setAttribute(PageConstant.PAGE_MODE.get(), PageConstant.EDIT.get());
        }

        ProjectComposite composite = new ProjectComposite(req);

        for (String param : Stream.of(ProjectParam.values()).map(ProjectParam::get).collect(Collectors.toList())){
            String error = composite.validate(param);
            if (!error.isEmpty()) {
                req.setAttribute(param + PageConstant.ERROR.get(), error);
                return false;
            }
        }
        return true;
    }
}
