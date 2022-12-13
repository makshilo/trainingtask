package com.qulix.shilomy.trainingtask.web.validator.task;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;

import javax.servlet.http.HttpServletRequest;

public class TaskNameValidator extends ValidatorChain {
    private static final String NAME_NULL_MESSAGE = "Наименование задачи не заполнено";

    private static TaskNameValidator instance;

    public static TaskNameValidator getInstance() {
        if (instance == null) {
            instance = new TaskNameValidator();
        }
        return instance;
    }

    private TaskNameValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (EmptinessCheck.isValid(req.getParameter(TaskParam.TASK_NAME.get()))) {
            return checkNext(req);
        }
        req.setAttribute(TaskParam.TASK_NAME.get() + TaskParam.ERROR.get(), NAME_NULL_MESSAGE);
        return false;
    }
}
