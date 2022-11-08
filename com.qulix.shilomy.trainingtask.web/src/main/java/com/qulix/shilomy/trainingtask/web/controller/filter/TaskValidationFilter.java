package com.qulix.shilomy.trainingtask.web.controller.filter;

import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;
import com.qulix.shilomy.trainingtask.web.validator.DateValidator;
import com.qulix.shilomy.trainingtask.web.validator.impl.DateValidatorImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

@WebFilter(filterName = "TaskValidationFilter", urlPatterns = {"/createTask", "/editTask"})
public class TaskValidationFilter implements Filter {
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String PROJECTS_PARAM_NAME = "projects";
    public static final String CURRENT_PROJECT_PARAM_NAME = "currentProject";
    public static final String PROJECT_LOCK_PARAM = "projectLock";
    public static final String TASK_STATUS_PARAM_NAME = "taskStatus";

    public static final String STATUS_PARAM_NAME = "status";
    public static final String TASK_NAME_PARAM = "taskName";
    public static final String PROJECT_PARAM_NAME = "project";
    public static final String WORK_PARAM_NAME = "work";
    public static final String START_YEAR_PARAM_NAME = "startYear";
    public static final String START_MONTH_PARAM_NAME = "startMonth";
    public static final String START_DAY_PARAM_NAME = "startDay";
    public static final String END_YEAR_PARAM_NAME = "endYear";
    public static final String END_MONTH_PARAM_NAME = "endMonth";
    public static final String END_DAY_PARAM_NAME = "endDay";
    public static final String EXECUTOR_PARAM_NAME = "executor";
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String EDIT_MODE = "edit";
    public static final String EMPTY_STRING = "";
    public static final String MINUS_SIGN = "-";

    public static final String DATE_FORMAT = "uuuu-MM-dd";
    public static final String YEAR_FORMAT = "uuuu";
    public static final String DAY_FORMAT = "dd";

    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
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

    private static final String EDIT_TASK_PAGE = "/jsp/editTask.jsp";
    public static final String EDIT_TASK = "/editTask";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    /**
     * Метод инициализации
     * @param filterConfig a <code>FilterConfig</code> обЪект который хранит параметры
     *               конфигурации и инициализации фильтра
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Метод который получает данные из запроса и проверяет их корректность,
     * если данные некорректны возвращает на страницу формы,
     * если данные корректны передаёт запрос следующему фильтру в цепочке.
     * @param request <code>ServletRequest</code> обЪект который хранит запрос пользователя
     * @param response <code>ServletResponse</code> объект который хранит ответ фильтра
     * @param chain <code>FilterChain</code> для вызова следующего ресурса фильтра
     * @throws IOException возникает в случае проблем с получением строки для перенаправления
     * @throws ServletException если в работе сервлета возникают проблемы
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String page = ((HttpServletRequest)request).getRequestURI();
        if (page.equals(EDIT_TASK)) {
            request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
        }

        TaskStatus status = TaskStatus.of(request.getParameter(STATUS_PARAM_NAME));
        String taskName = request.getParameter(TASK_NAME_PARAM);
        String projectId = request.getParameter(PROJECT_PARAM_NAME);
        String work = request.getParameter(WORK_PARAM_NAME);
        String startYear = request.getParameter(START_YEAR_PARAM_NAME);
        String startMonth = request.getParameter(START_MONTH_PARAM_NAME);
        String startDay = request.getParameter(START_DAY_PARAM_NAME);
        String endYear = request.getParameter(END_YEAR_PARAM_NAME);
        String endMonth = request.getParameter(END_MONTH_PARAM_NAME);
        String endDay = request.getParameter(END_DAY_PARAM_NAME);
        String executorId = request.getParameter(EXECUTOR_PARAM_NAME);

        DateValidator dateValidator = DateValidatorImpl.getInstance();

        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern(YEAR_FORMAT).withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern(DAY_FORMAT).withResolverStyle(ResolverStyle.STRICT);

        if (taskName == null || taskName.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, TASK_NAME_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (projectId == null || projectId.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, PROJECT_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (work == null || work.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WORK_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if(!isInt(work)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WORK_NOT_INTEGER);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (Integer.parseInt(work) < 0) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WORK_NEGATIVE);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (startYear == null || startYear.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, START_YEAR_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (!dateValidator.isValid(yearFormatter, startYear)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, INVALID_START_YEAR);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (startMonth == null || startMonth.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, START_MONTH_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (startDay == null || startDay.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, START_DAY_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (!dateValidator.isValid(dayFormatter, startDay)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, INVALID_START_DAY);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (!isDateValid(dateValidator, startYear, startMonth, startDay)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WRONG_START_DATE);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (endYear == null || endYear.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, END_YEAR_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (!dateValidator.isValid(yearFormatter, endYear)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, INVALID_END_YEAR);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (endMonth == null || endMonth.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, END_MONTH_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (endDay == null || endDay.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, END_DAY_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (!dateValidator.isValid(dayFormatter, endDay)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, INVALID_END_DAY);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (!isDateValid(dateValidator, endYear, endMonth, endDay)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WRONG_END_DATE);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (checkDateCollision(startYear + MINUS_SIGN + startMonth + MINUS_SIGN + startDay,
                                      endYear + MINUS_SIGN + endMonth + MINUS_SIGN + endDay)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, DATE_COLLISION);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (executorId == null || executorId.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, EXECUTOR_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else if (status == null) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, STATUS_NULL);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Метод который заполняет страницу данными, которые необходимы для её корректной работы.
     *
     * @param request объект {@link ServletRequest} который хранит запрос клиента,
     *                полученный от сервлета
     */
    public void fillPage(ServletRequest request) {
        request.setAttribute(EMPLOYEES_PARAM_NAME, employeeService.findAll());
        request.setAttribute(PROJECTS_PARAM_NAME, projectService.findAll());
        request.setAttribute(TASK_STATUS_PARAM_NAME, TaskStatus.values());
        if (request.getParameter(PROJECT_LOCK_PARAM) != null) {
            ProjectEntity currentProject = projectService.get(Long.parseLong(request.getParameter(CURRENT_PROJECT_PARAM_NAME)));
            request.setAttribute(PROJECT_LOCK_PARAM, true);
            request.setAttribute(CURRENT_PROJECT_PARAM_NAME, currentProject);
        }
    }

    /**
     * Метод который проверяет не пересекаются ли даты начала и окончания.
     * @param startDate дата начала
     * @param endDate дата окончания
     * @return true если даты пересекаются и false если нет.
     */
    private boolean checkDateCollision(String startDate, String endDate) {
        Date start = Date.valueOf(startDate);
        Date end = Date.valueOf(endDate);
        return !end.after(start) && !end.equals(start);
    }

    /**
     * Метод проверяет дату на существование и корректность.
     * @param dateValidator валидатор которым будет проверяться дата
     * @param year год
     * @param month месяц
     * @param day день
     * @return true если дата верна и false если нет.
     */
    private boolean isDateValid(DateValidator dateValidator, String year, String month, String day) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);

        return dateValidator.isValid(dateFormatter, year + MINUS_SIGN + month + MINUS_SIGN + day);
    }

    /**
     * Метод который проверяет являются ли данные в строке целым числом.
     * @param param проверяемая строка
     * @return true, если является целым числом, false если нет.
     */
    private boolean isInt(String param) {
        try {
            Integer.parseInt(param);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Метод завершения работы фильтра
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
