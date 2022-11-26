package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
import com.qulix.shilomy.trainingtask.web.controller.task.TaskFormParam;
import com.qulix.shilomy.trainingtask.web.validator.composite.TaskComposite;

import javax.servlet.http.HttpServletRequest;

/**
 * Класс валидатор который выполняет проверку параметров сущности задачи на корректность
 */
public class TaskValidator {
    public static final String EDIT_TASK = "/editTask";

    private TaskValidator() {

    }

    /**
     * Метод который получает данные сущности задачи из запроса и проверяет их корректность.
     * @param req <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
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
