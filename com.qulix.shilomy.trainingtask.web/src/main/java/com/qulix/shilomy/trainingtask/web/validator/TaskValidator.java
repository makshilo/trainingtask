package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;


import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс валидатор который выполняет проверку параметров сущности задачи на корректность
 */
public class TaskValidator {
    public static final String DATE_FORMAT = "uuuu-MM-dd";
    public static final String YEAR_FORMAT = "uuuu";
    public static final String DAY_FORMAT = "dd";

    public static final String TASK_NAME_NULL_MESSAGE = "Наименование задачи не заполнено";
    public static final String PROJECT_NULL_MESSAGE = "Проект не выбран";
    public static final String WORK_NULL_MESSAGE = "Работа не заполнена";
    public static final String WORK_NOT_INTEGER_MESSAGE = "Значение работы должно быть целым числом";
    public static final String WORK_NEGATIVE_MESSAGE = "Значение работы не должно быть отрицательным";
    public static final String START_YEAR_NULL_MESSAGE = "Год начала не заполнен";
    public static final String INVALID_START_YEAR_MESSAGE = "Введённое значение не соответствует формату: гггг";
    public static final String START_MONTH_NULL_MESSAGE = "Месяц начала не выбран";
    public static final String START_DAY_NULL_MESSAGE = "День начала не заполнен";
    public static final String INVALID_START_DAY_MESSAGE = "Введённое значение не соответствует формату: дд";
    public static final String WRONG_START_DATE_MESSAGE = "Введённая дата начала не существует";
    public static final String END_YEAR_NULL_MESSAGE = "Год окончания не заполнен";
    public static final String INVALID_END_YEAR_MESSAGE = "Введённое значение не соответствует формату: гггг";
    public static final String END_MONTH_NULL_MESSAGE = "Месяц окончания не выбран";
    public static final String END_DAY_NULL_MESSAGE = "День окончания не заполнен";
    public static final String INVALID_END_DAY_MESSAGE = "Введённое значение не соответствует формату: дд";
    public static final String WRONG_END_DATE_MESSAGE = "Введённая дата окончания не существует";
    public static final String DATE_COLLISION_MESSAGE = "Дата начала больше даты окончания";
    public static final String EXECUTOR_NULL_MESSAGE = "Исполнитель не выбран";
    public static final String STATUS_NULL_MESSAGE = "Статус не выбран";

    public static final String NAME_NULL = "nameNull";
    public static final String PROJECT_NULL = "projectNull";
    public static final String WORK_NULL = "workNull";
    public static final String WORK_NOT_INT = "workNotInt";
    public static final String WORK_NEGATIVE = "workNegative";
    public static final String START_YEAR_NULL = "startYearNull";
    public static final String START_YEAR_INVALID = "startYearInvalid";
    public static final String START_MONTH_NULL = "startMonthNull";
    public static final String START_DAY_NULL = "startDayNull";
    public static final String START_DAY_INVALID = "startDayInvalid";
    public static final String START_DATE_INVALID = "startDateInvalid";
    public static final String END_YEAR_NULL = "endYearNull";
    public static final String END_YEAR_INVALID = "endYearInvalid";
    public static final String END_MONTH_NULL = "endMonthNull";
    public static final String END_DAY_NULL = "endDayNull";
    public static final String END_DAY_INVALID = "endDayInvalid";
    public static final String END_DATE_INVALID = "endDateInvalid";
    public static final String DATE_COLLISION = "dateCollision";
    public static final String EXECUTOR_NULL = "executorNull";
    public static final String STATUS_NULL = "statusNull";

    public static final String EDIT_TASK = "/editTask";

    private TaskValidator() {

    }

    /**
     * Метод который получает данные сущности задачи из запроса и проверяет их корректность.
     * @param request <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @return true если все параметры проходят проверку, в остальных случаях false
     */
    public static Map<String, String> isValid(HttpServletRequest request) {
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

        Map<String, String> errors = new HashMap<>();

        if (taskName == null || taskName.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(NAME_NULL, TASK_NAME_NULL_MESSAGE);
        }
        if (projectId == null || projectId.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(PROJECT_NULL, PROJECT_NULL_MESSAGE);
        }
        if (work == null || work.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(WORK_NULL, WORK_NULL_MESSAGE);
        }else if(!isInt(work)) {
            errors.put(WORK_NOT_INT, WORK_NOT_INTEGER_MESSAGE);
        } else if (work.matches("^-[1-9]\\d*$")) {
            errors.put(WORK_NEGATIVE, WORK_NEGATIVE_MESSAGE);
        }
        if (startYear == null || startYear.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(START_YEAR_NULL, START_YEAR_NULL_MESSAGE);
        } else if (!DateValidator.isValid(yearFormatter, startYear)) {
            errors.put(START_YEAR_INVALID, INVALID_START_YEAR_MESSAGE);
        }
        if (startMonth == null || startMonth.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(START_MONTH_NULL, START_MONTH_NULL_MESSAGE);
        }
        if (startDay == null || startDay.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(START_DAY_NULL, START_DAY_NULL_MESSAGE);
        } else if (!DateValidator.isValid(dayFormatter, startDay)) {
            errors.put(START_DAY_INVALID, INVALID_START_DAY_MESSAGE);
        } else if (!DateValidator.isValid(dateFormatter, startYear + ControllerConstants.MINUS_SIGN +
                startMonth + ControllerConstants.MINUS_SIGN + startDay)) {
            errors.put(START_DATE_INVALID, WRONG_START_DATE_MESSAGE);
        }
        if (endYear == null || endYear.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(END_YEAR_NULL, END_YEAR_NULL_MESSAGE);
        } else if (!DateValidator.isValid(yearFormatter, endYear)) {
            errors.put(END_YEAR_INVALID, INVALID_END_YEAR_MESSAGE);
        }
        if (endMonth == null || endMonth.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(END_MONTH_NULL, END_MONTH_NULL_MESSAGE);
        }
        if (endDay == null || endDay.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(END_DAY_NULL, END_DAY_NULL_MESSAGE);
        } else if (!DateValidator.isValid(dayFormatter, endDay)) {
            errors.put(END_DAY_INVALID, INVALID_END_DAY_MESSAGE);
        } else if (!DateValidator.isValid(dateFormatter, endYear + ControllerConstants.MINUS_SIGN +
                endMonth + ControllerConstants.MINUS_SIGN + endDay)) {
            errors.put(END_DATE_INVALID, WRONG_END_DATE_MESSAGE);
        } else if (checkDateCollision(startYear + ControllerConstants.MINUS_SIGN + startMonth + ControllerConstants.MINUS_SIGN + startDay,
                endYear + ControllerConstants.MINUS_SIGN + endMonth + ControllerConstants.MINUS_SIGN + endDay)) {
            errors.put(DATE_COLLISION, DATE_COLLISION_MESSAGE);
        }
        if (executorId == null || executorId.equals(ControllerConstants.EMPTY_STRING)) {
            errors.put(EXECUTOR_NULL, EXECUTOR_NULL_MESSAGE);
        }
        if (status == null) {
            errors.put(STATUS_NULL, STATUS_NULL_MESSAGE);
        }

        return errors;
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
