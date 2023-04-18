package com.qulix.shilomy.trainingtask.web.validator.task;

import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.ValidatorChain;
import com.qulix.shilomy.trainingtask.web.validator.impl.EmptinessCheck;
import com.qulix.shilomy.trainingtask.web.validator.impl.RegexpCheck;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Валидатор даты начала
 */
public class StartDateValidator extends ValidatorChain {
    private static final String START_YEAR_NULL_MESSAGE = "Год начала не заполнен";
    private static final String INVALID_START_YEAR_MESSAGE = "Введённое значение не соответствует формату: гггг";
    private static final String START_DAY_NULL_MESSAGE = "День начала не заполнен";
    private static final String INVALID_START_DAY_MESSAGE = "Введённое значение не соответствует формату: дд";
    private static final String WRONG_START_DATE_MESSAGE = "Введённая дата начала не существует";

    private static final String YEAR_REGEX = "^(0{3}[1-9]|00[1-9][0-9]|0[1-9][0-9]{2}|[1-9][0-9]{3})";
    private static final String DAY_REGEX = "^(0[1-9]|[12][0-9]|3[01])";
    private static final String DATE_REGEX = "^(((\\d{4}-((0[13578]-|1[02]-)(0[1-9]|[12]\\d|3[01])|(0[13456789]-|1[012]-)(0[1-9]|[12]\\d|30)|02-(0[1-9]|1\\d|2[0-8])))|((([02468][048]|[13579][26])00|\\d{2}([13579][26]|0[48]|[2468][048])))-02-29)){0,10}$";

    /**
     * Единственный объект класса
     */
    private static StartDateValidator instance;

    /**
     * Получение объекта класса
     *
     * @return объект StartDateValidator
     */
    public static StartDateValidator getInstance() {
        if (instance == null) {
            instance = new StartDateValidator();
        }
        return instance;
    }

    private StartDateValidator() {

    }

    /**
     * Проверка даты начала
     *
     * @param req запрос
     * @return если проверка пройдена, результат следующего в цепи, иначе false
     */
    @Override
    public boolean check(HttpServletRequest req) {
        if (!EmptinessCheck.isValid(req.getParameter(TaskParam.START_YEAR.get()))) {
            req.setAttribute(TaskParam.START_YEAR.get() + TaskParam.ERROR.get(), START_YEAR_NULL_MESSAGE);
            return false;
        }

        if (!RegexpCheck.isValid(req.getParameter(TaskParam.START_YEAR.get()), YEAR_REGEX)) {
            req.setAttribute(TaskParam.START_YEAR.get() + TaskParam.ERROR.get(), INVALID_START_YEAR_MESSAGE);
            return false;
        }

        if (!EmptinessCheck.isValid(req.getParameter(TaskParam.START_DAY.get()))) {
            req.setAttribute(TaskParam.START_DAY.get() + TaskParam.ERROR.get(), START_DAY_NULL_MESSAGE);
            return false;
        }

        if (!RegexpCheck.isValid(req.getParameter(TaskParam.START_DAY.get()), DAY_REGEX)) {
            req.setAttribute(TaskParam.START_DAY.get() + TaskParam.ERROR.get(), INVALID_START_DAY_MESSAGE);
            return false;
        }

        if (!RegexpCheck.isValid(
                composeDate(
                        req.getParameter(TaskParam.START_YEAR.get()),
                        req.getParameter(TaskParam.START_MONTH.get()),
                        req.getParameter(TaskParam.START_DAY.get())),
                DATE_REGEX)) {
            req.setAttribute(TaskParam.START_DATE.get() + TaskParam.ERROR.get(), WRONG_START_DATE_MESSAGE);
            return false;
        }

        return checkNext(req);
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
