package com.qulix.shilomy.trainingtask.web.validator.task;

import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор наименования задачи
 */
public class TaskNameValidator extends ValidatorChain {
    private static final String NAME_NULL_MESSAGE = "Наименование задачи не заполнено";

    /**
     * Единственный объект класса
     */
    private static TaskNameValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект TaskNameValidator
     */
    public static TaskNameValidator getInstance() {
        if (instance == null) {
            instance = new TaskNameValidator();
        }
        return instance;
    }

    private TaskNameValidator() {

    }

    /**
     * Проверка наименования
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(TaskParam.TASK_NAME.get()))) {
            return checkNext(req);
        }
        req.setAttribute(TaskParam.TASK_NAME.get() + TaskParam.ERROR.get(), NAME_NULL_MESSAGE);
        return false;
    }
}
