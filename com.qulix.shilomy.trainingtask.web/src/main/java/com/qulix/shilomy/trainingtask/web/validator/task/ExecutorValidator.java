package com.qulix.shilomy.trainingtask.web.validator.task;

import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор исполнителя
 */
public class ExecutorValidator extends ValidatorChain {

    private static final String EXECUTOR_NULL = "Исполнитель не выбран";

    /**
     * Единственный объект класса
     */
    private static ExecutorValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект ExecutorValidator
     */
    public static ExecutorValidator getInstance() {
        if (instance == null) {
            instance = new ExecutorValidator();
        }
        return instance;
    }

    private ExecutorValidator() {

    }

    /**
     * Проверка исполнителя
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(TaskParam.EXECUTOR.get()))) {
            return checkNext(req);
        }
        req.setAttribute(TaskParam.EXECUTOR.get() + TaskParam.ERROR.get(), EXECUTOR_NULL);
        return false;
    }
}
