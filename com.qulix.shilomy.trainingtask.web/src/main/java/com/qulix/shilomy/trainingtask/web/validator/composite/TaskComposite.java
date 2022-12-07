package com.qulix.shilomy.trainingtask.web.validator.composite;

import com.qulix.shilomy.trainingtask.web.controller.task.TaskParam;
import com.qulix.shilomy.trainingtask.web.validator.Validator;
import com.qulix.shilomy.trainingtask.web.validator.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Хранилище проверок задачи
 */
public class TaskComposite {

    // Таблица соответствия параметров и их валидаторов
    private final Map<String, Validator> taskValidators = new HashMap<>();

    public static final String NAME_NULL_MESSAGE = "Наименование задачи не заполнено";
    public static final String WORK_NULL_MESSAGE = "Работа не заполнена";
    public static final String WORK_INVALID_MESSAGE = "Значение работы должно быть целым, положительным числом";
    public static final String START_YEAR_NULL_MESSAGE = "Год начала не заполнен";
    public static final String INVALID_START_YEAR_MESSAGE = "Введённое значение не соответствует формату: гггг";
    public static final String START_DAY_NULL_MESSAGE = "День начала не заполнен";
    public static final String INVALID_START_DAY_MESSAGE = "Введённое значение не соответствует формату: дд";
    public static final String WRONG_START_DATE_MESSAGE = "Введённая дата начала не существует";
    public static final String END_YEAR_NULL_MESSAGE = "Год окончания не заполнен";
    public static final String INVALID_END_YEAR_MESSAGE = "Введённое значение не соответствует формату: гггг";
    public static final String END_DAY_NULL_MESSAGE = "День окончания не заполнен";
    public static final String INVALID_END_DAY_MESSAGE = "Введённое значение не соответствует формату: дд";
    public static final String WRONG_END_DATE_MESSAGE = "Введённая дата окончания не существует";
    public static final String DATE_COLLISION_MESSAGE = "Дата начала больше даты окончания";

    public static final String WORK_REGEX = "^[0-9]+";
    public static final String YEAR_REGEX = "^(0{3}[1-9]|00[1-9][0-9]|0[1-9][0-9]{2}|[1-9][0-9]{3})";
    public static final String DAY_REGEX = "^(0[1-9]|[12][0-9]|3[01])";
    public static final String DATE_REGEX = "^(((\\d{4}-((0[13578]-|1[02]-)(0[1-9]|[12]\\d|3[01])|(0[13456789]-|1[012]-)(0[1-9]|[12]\\d|30)|02-(0[1-9]|1\\d|2[0-8])))|((([02468][048]|[13579][26])00|\\d{2}([13579][26]|0[48]|[2468][048])))-02-29)){0,10}$";

    /**
     * Конструктор с заполнением таблицы валидаторов
     * @param req запрос клиента
     */
    public TaskComposite(HttpServletRequest req) {
        taskValidators.put(TaskParam.TASK_NAME.get(), new CompositeValidator(
                new EmptinessValidator(NAME_NULL_MESSAGE, req.getParameter(TaskParam.TASK_NAME.get()))
        ));

        taskValidators.put(TaskParam.PROJECT.get(), new CompositeValidator(
                new EmptinessValidator("Проект отсутствует", req.getParameter(TaskParam.PROJECT.get()))
        ));

        taskValidators.put(TaskParam.WORK.get(), new CompositeValidator(
                new EmptinessValidator(WORK_NULL_MESSAGE, req.getParameter(TaskParam.WORK.get())),
                new RegexpValidator(WORK_INVALID_MESSAGE, req.getParameter(TaskParam.WORK.get()), WORK_REGEX)
        ));

        taskValidators.put(TaskParam.START_YEAR.get(), new CompositeValidator(
                new EmptinessValidator(START_YEAR_NULL_MESSAGE, req.getParameter(TaskParam.START_YEAR.get())),
                new RegexpValidator(INVALID_START_YEAR_MESSAGE, req.getParameter(TaskParam.START_YEAR.get()), YEAR_REGEX)
        ));

        taskValidators.put(TaskParam.START_DAY.get(), new CompositeValidator(
                new EmptinessValidator(START_DAY_NULL_MESSAGE, req.getParameter(TaskParam.START_DAY.get())),
                new RegexpValidator(INVALID_START_DAY_MESSAGE, req.getParameter(TaskParam.START_DAY.get()), DAY_REGEX)
        ));

        taskValidators.put(TaskParam.START_DATE.get(), new CompositeValidator(
                new RegexpValidator(
                        WRONG_START_DATE_MESSAGE,
                        composeDate(
                                req.getParameter(TaskParam.START_YEAR.get()),
                                req.getParameter(TaskParam.START_MONTH.get()),
                                req.getParameter(TaskParam.START_DAY.get())),
                        DATE_REGEX
                )
        ));

        taskValidators.put(TaskParam.END_YEAR.get(), new CompositeValidator(
                new EmptinessValidator(END_YEAR_NULL_MESSAGE, req.getParameter(TaskParam.END_YEAR.get())),
                new RegexpValidator(INVALID_END_YEAR_MESSAGE, req.getParameter(TaskParam.END_YEAR.get()), YEAR_REGEX)
        ));

        taskValidators.put(TaskParam.END_DAY.get(), new CompositeValidator(
                new EmptinessValidator(END_DAY_NULL_MESSAGE, req.getParameter(TaskParam.END_DAY.get())),
                new RegexpValidator(INVALID_END_DAY_MESSAGE, req.getParameter(TaskParam.END_DAY.get()), DAY_REGEX)
        ));

        taskValidators.put(TaskParam.END_DATE.get(), new CompositeValidator(
                new RegexpValidator(
                        WRONG_END_DATE_MESSAGE,
                        composeDate(
                                req.getParameter(TaskParam.END_YEAR.get()),
                                req.getParameter(TaskParam.END_MONTH.get()),
                                req.getParameter(TaskParam.END_DAY.get())),
                        DATE_REGEX)
        ));

        taskValidators.put(TaskParam.DATE_COLLISION.get(), new CompositeValidator(
                new DateCollisionValidator(DATE_COLLISION_MESSAGE,
                        composeDate(req.getParameter(TaskParam.START_YEAR.get()),
                                req.getParameter(TaskParam.START_MONTH.get()),
                                req.getParameter(TaskParam.START_DAY.get())),
                        composeDate(req.getParameter(TaskParam.END_YEAR.get()),
                                req.getParameter(TaskParam.END_MONTH.get()),
                                req.getParameter(TaskParam.END_DAY.get())))
        ));

        taskValidators.put(TaskParam.EXECUTOR.get(), new CompositeValidator(
                new EmptinessValidator("Исполнитель отсутствует", req.getParameter(TaskParam.EXECUTOR.get()))
        ));
    }

    /**
     * Объединение даты в одну строку
     * @param year год
     * @param month месяц
     * @param day день
     * @return дата в формате гггг-мм-дд
     */
    private String composeDate(String year, String month, String day) {
        return String.join(TaskParam.MINUS.get(), year, month, day);
    }

    /**
     * Метод валидации
     * @param paramName имя параметра
     * @return строка ошибки или пустая строка если параметр верен
     */
    public String validate(String paramName) {
        Validator validator = taskValidators.get(paramName);

        return validator != null ? validator.isValid() : "";
    }
}