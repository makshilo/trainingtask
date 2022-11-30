package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
import com.qulix.shilomy.trainingtask.web.controller.task.TaskFormParam;
import com.qulix.shilomy.trainingtask.web.validator.composite.TaskComposite;

import javax.servlet.http.HttpServletRequest;

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
            req.setAttribute(ControllerConstant.PAGE_MODE_PARAM_NAME.get(), ControllerConstant.EDIT_MODE.get());
        }

        TaskComposite composite = new TaskComposite(req);

        for (String param : TaskFormParam.getStringValues()){
            String error = composite.validate(param);
            if (!error.isEmpty()) {
                req.setAttribute(param + ControllerConstant.ERROR_MESSAGES_PARAM.get(), error);
                return false;
            }
        }
        return true;
    }
}
