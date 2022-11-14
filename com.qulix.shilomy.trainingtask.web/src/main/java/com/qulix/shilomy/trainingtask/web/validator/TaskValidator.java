package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;


import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Класс валидатор который выполняет проверку параметров сущности задачи на корректность
 */
public class TaskValidator {
    public static final String DATE_FORMAT = "uuuu-MM-dd";
    public static final String YEAR_FORMAT = "uuuu";
    public static final String DAY_FORMAT = "dd";

    public static final String TASK_NAME_NULL = "Наименование задачи не заполнено";
    public static final String PROJECT_NULL = "Проект не выбран";
    public static final String WORK_NULL = "Работа не заполнена";
    public static final String WORK_NOT_INTEGER = "Значение работы должно быть целым числом";
    public static final String WORK_NEGATIVE = "Значение работы не должно быть отрицательным";
    public static final String START_YEAR_NULL = "Год начала не заполнен";
    public static final String INVALID_START_YEAR = "Введённое значение не соответствует формату: гггг";
    public static final String START_MONTH_NULL = "Месяц начала не выбран";
    public static final String START_DAY_NULL = "День начала не заполнен";
    public static final String INVALID_START_DAY = "Введённое значение не соответствует формату: дд";
    public static final String WRONG_START_DATE = "Введённая дата начала не существует";
    public static final String END_YEAR_NULL = "Год окончания не заполнен";
    public static final String INVALID_END_YEAR = "Введённое значение не соответствует формату: гггг";
    public static final String END_MONTH_NULL = "Месяц окончания не выбран";
    public static final String END_DAY_NULL = "День окончания не заполнен";
    public static final String INVALID_END_DAY = "Введённое значение не соответствует формату: дд";
    public static final String WRONG_END_DATE = "Введённая дата окончания не существует";
    public static final String DATE_COLLISION = "Дата начала больше даты окончания";
    public static final String EXECUTOR_NULL = "Исполнитель не выбран";
    public static final String STATUS_NULL = "Статус не выбран";

    public static final String EDIT_TASK = "/editTask";

    private TaskValidator() {

    }

    /**
     * Метод который получает данные сущности задачи из запроса и проверяет их корректность.
     * @param request <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
     */
    public static boolean isValid(HttpServletRequest request) {
        String page = request.getRequestURI();
        if (page.equals(EDIT_TASK)) {
            request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME, ControllerConstants.EDIT_MODE);
        }

        TaskStatus status = TaskStatus.of(request.getParameter(ControllerConstants.STATUS_PARAM_NAME));
        String taskName = request.getParameter(ControllerConstants.TASK_NAME_PARAM);
        String projectId = request.getParameter(ControllerConstants.PROJECT_PARAM_NAME);
        String work = request.getParameter(ControllerConstants.WORK_PARAM_NAME);
        String startYear = request.getParameter(ControllerConstants.START_YEAR_PARAM_NAME);
        String startMonth = request.getParameter(ControllerConstants.START_MONTH_PARAM_NAME);
        String startDay = request.getParameter(ControllerConstants.START_DAY_PARAM_NAME);
        String endYear = request.getParameter(ControllerConstants.END_YEAR_PARAM_NAME);
        String endMonth = request.getParameter(ControllerConstants.END_MONTH_PARAM_NAME);
        String endDay = request.getParameter(ControllerConstants.END_DAY_PARAM_NAME);
        String executorId = request.getParameter(ControllerConstants.EXECUTOR_PARAM_NAME);

        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern(YEAR_FORMAT).withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern(DAY_FORMAT).withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);

        if (taskName == null || taskName.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, TASK_NAME_NULL);
            return false;
        } else if (projectId == null || projectId.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, PROJECT_NULL);
            return false;
        } else if (work == null || work.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, WORK_NULL);
            return false;
        } else if(!isInt(work)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, WORK_NOT_INTEGER);
            return false;
        } else if (Integer.parseInt(work) < 0) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, WORK_NEGATIVE);
            return false;
        } else if (startYear == null || startYear.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, START_YEAR_NULL);
            return false;
        } else if (!DateValidator.isValid(yearFormatter, startYear)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, INVALID_START_YEAR);
            return false;
        } else if (startMonth == null || startMonth.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, START_MONTH_NULL);
            return false;
        } else if (startDay == null || startDay.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, START_DAY_NULL);
            return false;
        } else if (!DateValidator.isValid(dayFormatter, startDay)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, INVALID_START_DAY);
            return false;
        } else if (!DateValidator.isValid(dateFormatter, startYear + ControllerConstants.MINUS_SIGN +
                startMonth + ControllerConstants.MINUS_SIGN + startDay)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, WRONG_START_DATE);
            return false;
        } else if (endYear == null || endYear.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, END_YEAR_NULL);
            return false;
        } else if (!DateValidator.isValid(yearFormatter, endYear)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, INVALID_END_YEAR);
            return false;
        } else if (endMonth == null || endMonth.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, END_MONTH_NULL);
            return false;
        } else if (endDay == null || endDay.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, END_DAY_NULL);
            return false;
        } else if (!DateValidator.isValid(dayFormatter, endDay)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, INVALID_END_DAY);
            return false;
        } else if (!DateValidator.isValid(dateFormatter, endYear + ControllerConstants.MINUS_SIGN +
                endMonth + ControllerConstants.MINUS_SIGN + endDay)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, WRONG_END_DATE);
            return false;
        } else if (checkDateCollision(startYear + ControllerConstants.MINUS_SIGN + startMonth + ControllerConstants.MINUS_SIGN + startDay,
                endYear + ControllerConstants.MINUS_SIGN + endMonth + ControllerConstants.MINUS_SIGN + endDay)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, DATE_COLLISION);
            return false;
        } else if (executorId == null || executorId.equals(ControllerConstants.EMPTY_STRING)) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, EXECUTOR_NULL);
            return false;
        } else if (status == null) {
            request.setAttribute(ControllerConstants.VALIDATION_ERROR_PARAM_NAME, STATUS_NULL);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Метод который проверяет не пересекаются ли даты начала и окончания.
     * @param startDate дата начала
     * @param endDate дата окончания
     * @return true если даты пересекаются и false если нет.
     */
    private static boolean checkDateCollision(String startDate, String endDate) {
        Date start = Date.valueOf(startDate);
        Date end = Date.valueOf(endDate);
        return !end.after(start) && !end.equals(start);
    }

    /**
     * Метод который проверяет являются ли данные в строке целым числом.
     * @param param проверяемая строка
     * @return true, если является целым числом, false если нет.
     */
    private static boolean isInt(String param) {
        try {
            Integer.parseInt(param);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
