package com.qulix.shilomy.trainingtask.web.validator.task;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;

import javax.servlet.http.HttpServletRequest;

public class TaskProjectValidator extends ValidatorChain {

    private static final String PROJECT_NULL = "Проект не выбран";

    private static TaskProjectValidator instance;

    public static TaskProjectValidator getInstance() {
        if (instance == null) {
            instance = new TaskProjectValidator();
        }
        return instance;
    }

    private TaskProjectValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(TaskParam.PROJECT.get()))) {
            return checkNext(req);
        }
        req.setAttribute(TaskParam.PROJECT.get() + TaskParam.ERROR.get(), PROJECT_NULL);
        return false;
    }
}
