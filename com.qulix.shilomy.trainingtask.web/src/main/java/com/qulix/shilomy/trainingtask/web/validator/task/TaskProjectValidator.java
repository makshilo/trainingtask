package com.qulix.shilomy.trainingtask.web.validator.task;

import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор проекта задачи
 */
public class TaskProjectValidator extends ValidatorChain {

    private static final String PROJECT_NULL = "Проект не выбран";

    /**
     * Единственный объект класса
     */
    private static TaskProjectValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект TaskProjectValidator
     */
    public static TaskProjectValidator getInstance() {
        if (instance == null) {
            instance = new TaskProjectValidator();
        }
        return instance;
    }

    private TaskProjectValidator() {

    }

    /**
     * Проверка проекта задачи
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(TaskParam.PROJECT.get()))) {
            return checkNext(req);
        }
        req.setAttribute(TaskParam.PROJECT.get() + TaskParam.ERROR.get(), PROJECT_NULL);
        return false;
    }
}
