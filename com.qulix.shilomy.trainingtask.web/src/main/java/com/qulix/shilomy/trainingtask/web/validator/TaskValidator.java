package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.task.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Валидатор задачи
 */
public class TaskValidator {
    public static final String EDIT_TASK = "/editTask";

    /**
     * Цепочка валидаторов задачи
     */
    private static final ValidatorChain taskValidatorChain = ValidatorChain.link(
            TaskNameValidator.getInstance(),
            TaskProjectValidator.getInstance(),
            WorkValidator.getInstance(),
            StartDateValidator.getInstance(),
            EndDateValidator.getInstance(),
            DateCollisionValidator.getInstance(),
            ExecutorValidator.getInstance()
    );

    private TaskValidator() {

    }

    /**
     * Запуск цепочки проверок задачи
     *
     * @param req <code>ServletRequest</code> запрос
     * @return true когда все параметры корректны, в остальных случаях false
     */
    public static boolean isValid(HttpServletRequest req) {
        String page = req.getRequestURI();
        if (page.equals(EDIT_TASK)) {
            req.setAttribute(TaskParam.PAGE_MODE.get(), TaskParam.EDIT.get());
        }

        return taskValidatorChain.check(req);
    }
}
