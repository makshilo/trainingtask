package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.PageConstant;
import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.composite.TaskComposite;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Валидатор параметров задачи
 */
public class TaskValidator {
    public static final String EDIT_TASK = "/editTask";

    private TaskValidator() {

    }

    /**
     * Проверка параметров и добавление ошибок на страницу
     * @param req <code>ServletRequest</code> запрос пользователя
     * @return true когда все параметры корректны, в остальных случаях false
     */
    public static boolean isValid(HttpServletRequest req) {
        String page = req.getRequestURI();
        if (page.equals(EDIT_TASK)) {
            req.setAttribute(PageConstant.PAGE_MODE.get(), PageConstant.EDIT.get());
        }

        TaskComposite composite = new TaskComposite(req);

        for (String param : Stream.of(TaskParam.values()).map(TaskParam::get).collect(Collectors.toList())){
            String error = composite.validate(param);
            if (!error.isEmpty()) {
                req.setAttribute(param + PageConstant.ERROR.get(), error);
                return false;
            }
        }
        return true;
    }
}
