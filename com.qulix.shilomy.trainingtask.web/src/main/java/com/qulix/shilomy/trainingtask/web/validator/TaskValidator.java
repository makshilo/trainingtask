package com.qulix.shilomy.trainingtask.web.validator;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.controller.project.ProjectFormParams;
import com.qulix.shilomy.trainingtask.web.controller.task.TaskFormParams;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;


import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс валидатор который выполняет проверку параметров сущности задачи на корректность
 */
public class TaskValidator {
    public static final String TASK_NAME_NULL_MESSAGE = "Наименование задачи не заполнено";
    public static final String PROJECT_NULL_MESSAGE = "Проект не выбран";
    public static final String WORK_NULL_MESSAGE = "Работа не заполнена";
    public static final String WORK_INVALID_MESSAGE = "Значение работы должно быть целым, положительным числом";
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

    public static final String WORK_REGEX = "^[0-9]+";
    public static final String YEAR_REGEX = "^(0{3}[1-9]|00[1-9][0-9]|0[1-9][0-9]{2}|[1-9][0-9]{3})";
    public static final String DAY_REGEX = "^(0[1-9]|[12][0-9]|3[01])";

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
            request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME.get(), ControllerConstants.EDIT_MODE.get());
        }

        TaskStatus status = TaskStatus.of(request.getParameter(TaskFormParams.STATUS_PARAM.get()));
        String taskName = request.getParameter(TaskFormParams.TASK_NAME.get());
        String projectId = request.getParameter(ProjectFormParams.PROJECT_PARAM.get());
        String work = request.getParameter(TaskFormParams.WORK_PARAM.get());
        String startYear = request.getParameter(TaskFormParams.START_YEAR_PARAM.get());
        String startMonth = request.getParameter(TaskFormParams.START_MONTH_PARAM.get());
        String startDay = request.getParameter(TaskFormParams.START_DAY_PARAM.get());
        String endYear = request.getParameter(TaskFormParams.END_YEAR_PARAM.get());
        String endMonth = request.getParameter(TaskFormParams.END_MONTH_PARAM.get());
        String endDay = request.getParameter(TaskFormParams.END_DAY_PARAM.get());
        String executorId = request.getParameter(TaskFormParams.EXECUTOR_PARAM.get());

        Map<String, String> errors = new HashMap<>();

        if (taskName == null || taskName.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(NAME_NULL, TASK_NAME_NULL_MESSAGE);
        }
        if (projectId == null || projectId.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(PROJECT_NULL, PROJECT_NULL_MESSAGE);
        }
        if (work == null || work.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(WORK_NULL, WORK_NULL_MESSAGE);
        } else if(!work.matches(WORK_REGEX)) {
            errors.put(WORK_NOT_INT, WORK_INVALID_MESSAGE);
        }

        if (startYear == null || startYear.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(START_YEAR_NULL, START_YEAR_NULL_MESSAGE);
        } else if (!startYear.matches(YEAR_REGEX)) {
            errors.put(START_YEAR_INVALID, INVALID_START_YEAR_MESSAGE);
        } else if (startMonth == null || startMonth.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(START_MONTH_NULL, START_MONTH_NULL_MESSAGE);
        } else if (startDay == null || startDay.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(START_DAY_NULL, START_DAY_NULL_MESSAGE);
        } else if (!startDay.matches(DAY_REGEX)) {
            errors.put(START_DAY_INVALID, INVALID_START_DAY_MESSAGE);
        } else if (!DateValidator.isValid(startYear + ControllerConstants.MINUS_SIGN.get() +
                startMonth + ControllerConstants.MINUS_SIGN.get() + startDay)) {
            errors.put(START_DATE_INVALID, WRONG_START_DATE_MESSAGE);
        }

        if (endYear == null || endYear.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(END_YEAR_NULL, END_YEAR_NULL_MESSAGE);
        } else if (!endYear.matches(YEAR_REGEX)) {
            errors.put(END_YEAR_INVALID, INVALID_END_YEAR_MESSAGE);
        } else if (endMonth == null || endMonth.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(END_MONTH_NULL, END_MONTH_NULL_MESSAGE);
        } else if (endDay == null || endDay.equals(ControllerConstants.EMPTY_STRING.get())) {
            errors.put(END_DAY_NULL, END_DAY_NULL_MESSAGE);
        } else if (!endDay.matches(DAY_REGEX)) {
            errors.put(END_DAY_INVALID, INVALID_END_DAY_MESSAGE);
        } else if (!DateValidator.isValid(endYear + ControllerConstants.MINUS_SIGN.get() +
                endMonth + ControllerConstants.MINUS_SIGN.get() + endDay)) {
            errors.put(END_DATE_INVALID, WRONG_END_DATE_MESSAGE);
        } else if (checkDateCollision(startYear + ControllerConstants.MINUS_SIGN.get() + startMonth + ControllerConstants.MINUS_SIGN.get() + startDay,
                endYear + ControllerConstants.MINUS_SIGN.get() + endMonth + ControllerConstants.MINUS_SIGN.get() + endDay)) {
            errors.put(DATE_COLLISION, DATE_COLLISION_MESSAGE);
        }
        if (executorId == null || executorId.equals(ControllerConstants.EMPTY_STRING.get())) {
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
}
