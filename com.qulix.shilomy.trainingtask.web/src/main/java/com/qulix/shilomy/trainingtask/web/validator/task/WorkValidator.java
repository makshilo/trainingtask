package com.qulix.shilomy.trainingtask.web.validator.task;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import com.qulix.shilomy.trainingtask.web.validator.impl.RegexpCheck;

import javax.servlet.http.HttpServletRequest;

public class WorkValidator extends ValidatorChain {
    private static final String WORK_NULL_MESSAGE = "Работа не заполнена";
    private static final String WORK_INVALID_MESSAGE = "Значение работы должно быть целым, положительным числом";

    private static final String WORK_REGEX = "^[0-9]+";

    private static WorkValidator instance;

    public static WorkValidator getInstance() {
        if (instance == null) {
            instance = new WorkValidator();
        }
        return instance;
    }

    private WorkValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (!EmptinessCheck.isValid(req.getParameter(TaskParam.WORK.get()))) {
            req.setAttribute(TaskParam.WORK.get() + TaskParam.ERROR.get(), WORK_NULL_MESSAGE);
            return false;
        }

        if (!RegexpCheck.isValid(req.getParameter(TaskParam.WORK.get()), WORK_REGEX)) {
            req.setAttribute(TaskParam.WORK.get() + TaskParam.ERROR.get(), WORK_INVALID_MESSAGE);
            return false;
        }

        return checkNext(req);
    }
}
