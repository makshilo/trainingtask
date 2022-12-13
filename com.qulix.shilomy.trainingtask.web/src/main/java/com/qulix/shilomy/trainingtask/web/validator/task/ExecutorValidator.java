package com.qulix.shilomy.trainingtask.web.validator.task;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;

import javax.servlet.http.HttpServletRequest;

public class ExecutorValidator extends ValidatorChain {

    private static final String EXECUTOR_NULL = "Исполнитель не выбран";

    private static ExecutorValidator instance;

    public static ExecutorValidator getInstance() {
        if (instance == null) {
            instance = new ExecutorValidator();
        }
        return instance;
    }

    private ExecutorValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(TaskParam.EXECUTOR.get()))) {
            return checkNext(req);
        }
        req.setAttribute(TaskParam.EXECUTOR.get() + TaskParam.ERROR.get(), EXECUTOR_NULL);
        return false;
    }
}
