package com.qulix.shilomy.trainingtask.web.validator.task;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.DateCollisionCheck;

import javax.servlet.http.HttpServletRequest;

public class DateCollisionValidator extends ValidatorChain {
    private static final String DATE_COLLISION_MESSAGE = "Дата начала больше даты окончания";

    private static DateCollisionValidator instance;

    public static DateCollisionValidator getInstance() {
        if (instance == null) {
            instance = new DateCollisionValidator();
        }
        return instance;
    }

    private DateCollisionValidator() {

    }

    @Override
    public boolean check(HttpServletRequest req) {
        if (DateCollisionCheck.isValid(
                composeDate(
                        req.getParameter(TaskParam.START_YEAR.get()),
                        req.getParameter(TaskParam.START_MONTH.get()),
                        req.getParameter(TaskParam.START_DAY.get())
                ),
                composeDate(
                        req.getParameter(TaskParam.END_YEAR.get()),
                        req.getParameter(TaskParam.END_MONTH.get()),
                        req.getParameter(TaskParam.END_DAY.get())
                )
        )) {
            return checkNext(req);
        }
        req.setAttribute(TaskParam.DATE_COLLISION.get() + TaskParam.ERROR.get(), DATE_COLLISION_MESSAGE);
        return false;
    }

    /**
     * Объединение даты в одну строку
     *
     * @param year  год
     * @param month месяц
     * @param day   день
     * @return дата в формате гггг-мм-дд
     */
    private String composeDate(String year, String month, String day) {
        return String.join(TaskParam.MINUS.get(), year, month, day);
    }
}
