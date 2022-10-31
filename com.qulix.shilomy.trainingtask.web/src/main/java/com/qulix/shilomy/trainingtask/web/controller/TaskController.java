package com.qulix.shilomy.trainingtask.web.controller;

import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;
import com.qulix.shilomy.trainingtask.web.service.TaskService;
import com.qulix.shilomy.trainingtask.web.validator.DateValidator;
import com.qulix.shilomy.trainingtask.web.validator.impl.DateValidatorImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashMap;

/**
 * Http сервлет для работы с задачами(TaskEntity)
 */
@WebServlet("/tasks")
public class TaskController extends HttpServlet {

    public static final String ACTION_PARAM_NAME = "action";
    public static final String TASKS_PARAM_NAME = "tasks";
    public static final String TASK_PARAM_NAME = "task";
    public static final String ID_PARAM_NAME = "id";
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String PROJECTS_PARAM_NAME = "projects";
    public static final String CURRENT_PROJECT_PARAM_NAME = "currentProject";
    public static final String PROJECT_LOCK_PARAM = "projectLock";
    public static final String TASK_STATUS_PARAM_NAME = "taskStatus";
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
    public static final String CREATE_MODE = "create";
    public static final String EDIT_MODE = "edit";

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
    public static final String EMPTY_STRING = "";
    public static final String MINUS_SIGN = "-";

    public static final String DATE_FORMAT = "uuuu-MM-dd";
    public static final String YEAR_FORMAT = "uuuu";
    public static final String DAY_FORMAT = "dd";

    public static final String VALIDATION_ERROR_PARAM_NAME = "validationError";
    public static final String TASK_NAME_NULL = "taskNameNull";
    public static final String PROJECT_NULL = "projectNull";
    public static final String WORK_NULL = "workNull";
    public static final String WORK_NOT_INTEGER = "workNotInteger";
    public static final String WORK_NEGATIVE = "workNegative";
    public static final String START_YEAR_NULL = "startYearNull";
    public static final String INVALID_START_YEAR = "invalidStartYear";
    public static final String START_MONTH_NULL = "startMonthNull";
    public static final String START_DAY_NULL = "startDayNull";
    public static final String INVALID_START_DAY = "invalidStartDay";
    public static final String WRONG_START_DATE = "wrongStartDate";
    public static final String END_YEAR_NULL = "endYearNull";
    public static final String INVALID_END_YEAR = "invalidEndYear";
    public static final String END_MONTH_NULL = "endMonthNull";
    public static final String END_DAY_NULL = "endDayNull";
    public static final String INVALID_END_DAY = "invalidEndDay";
    public static final String WRONG_END_DATE = "wrongEndDate";
    public static final String EXECUTOR_NULL = "executorNull";
    public static final String STATUS_NULL = "statusNull";
    public static final String DATE_COLLISION = "dateCollision";

    public static final String TASK_LIST_ACTION_NAME = "taskList";
    public static final String CREATE_TASK_PAGE_ACTION_NAME = "createTaskPage";
    public static final String CREATE_TASK_ACTION_NAME = "createTask";
    public static final String TASK_EDIT_PAGE_ACTION_NAME = "taskEditPage";
    public static final String EDIT_TASK_ACTION_NAME = "editTask";
    public static final String DELETE_TASK_ACTION_NAME = "deleteTask";

    private static final String TASK_LIST_PAGE = "/jsp/taskList.jsp";
    private static final String EDIT_TASK_PAGE = "/jsp/editTask.jsp";
    private static final String COMMAND_TASK_LIST = "/tasks?action=taskList";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);
    private final TaskService taskService = (TaskService) serviceFactory.serviceFor(TaskEntity.class);
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);

    /**
     * Метод обработки POST запросов
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                 полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws ServletException если в работе сервлета возникают проблемы
     * @throws IOException возникает в случае проблем с получением/записью данных
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Метод обработки GET запросов, получает действие из запроса и вызывает соответствующий метод
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                       полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                        отправляемый сервлетом клиенту
     *
     * @throws ServletException если в работе сервлета возникают проблемы
     * @throws IOException возникает в случае проблем с получением/записью данных
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION_PARAM_NAME);

        switch (action) {
            case TASK_LIST_ACTION_NAME:
                taskList(request, response);
                break;
            case CREATE_TASK_PAGE_ACTION_NAME:
                showTaskForm(request, response, CREATE_MODE);
                break;
            case CREATE_TASK_ACTION_NAME:
                createTask(request, response);
                break;
            case TASK_EDIT_PAGE_ACTION_NAME:
                showTaskForm(request, response, EDIT_MODE);
                break;
            case EDIT_TASK_ACTION_NAME:
                editTask(request, response);
                break;
            case DELETE_TASK_ACTION_NAME:
                deleteTask(request, response);
                break;
        }
    }

    /**
     * Метод который заполняет страницу списка задач,
     * а затем перенаправляет на неё
     * @param request запрос клиента
     * @param response ответ сервера
     * @throws ServletException если в работе сервлета возникают проблемы
     * @throws IOException возникает в случае проблем с получением/записью данных
     */
    private void taskList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(TASKS_PARAM_NAME, taskService.findAll());
        request.setAttribute(EMPLOYEES_PARAM_NAME, getEmployeeNames());
        request.setAttribute(PROJECTS_PARAM_NAME, getProjectNames());
        RequestDispatcher dispatcher = request.getRequestDispatcher(TASK_LIST_PAGE);
        dispatcher.forward(request, response);
    }

    /**
     * Метод который отображает страницу формы задачи и заполняет её необходимыми для отображения данными
     * в зависимости от режима(pageMode):
     * EDIT_MODE - производится поиск и добавление задачи в форму для редактирования
     * CREATE_MODE - поля формы остаются пустыми для добавления задачи
     * @param request запрос клиента
     * @param response ответ сервера
     * @param pageMode режим работы формы
     * @throws ServletException если в работе сервлета возникают проблемы
     * @throws IOException возникает в случае проблем с получением/записью данных
     */
    private void showTaskForm(HttpServletRequest request, HttpServletResponse response, String pageMode) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_TASK_PAGE);
        if (pageMode.equals(EDIT_MODE)) {
            TaskEntity task = taskService.get(Long.parseLong(request.getParameter(ID_PARAM_NAME)));
            request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
            request.setAttribute(TASK_PARAM_NAME, task);
        } else {
            request.setAttribute(PAGE_MODE_PARAM_NAME, CREATE_MODE);
        }
        request.setAttribute(EMPLOYEES_PARAM_NAME, employeeService.findAll());
        request.setAttribute(PROJECTS_PARAM_NAME, projectService.findAll());
        request.setAttribute(TASK_STATUS_PARAM_NAME, TaskStatus.values());
        if (request.getParameter(PROJECT_LOCK_PARAM) != null) {
            ProjectEntity currentProject = projectService.get(Long.parseLong(request.getParameter(CURRENT_PROJECT_PARAM_NAME)));
            request.setAttribute(PROJECT_LOCK_PARAM, true);
            request.setAttribute(CURRENT_PROJECT_PARAM_NAME, currentProject);
        }
        dispatcher.forward(request, response);
    }

    /**
     * Метод который получает из формы параметры задачи и по результатам проверки
     * либо добавляет новую и перенаправляет на страницу списка задач
     * либо возвращает на страницу формы
     * @param request запрос клиента
     * @param response ответ сервера
     * @throws IOException возникает в случае проблем с получением/записью данных
     * @throws ServletException если в работе сервлета возникают проблемы
     */
    private void createTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        if (validateFields(request, taskName, projectId, work,
                startYear, startMonth, startDay,
                endYear, endMonth, endDay,
                executorId, status))
        {
            Date startDate = Date.valueOf(startYear + MINUS_SIGN + startMonth + MINUS_SIGN + startDay);
            Date endDate = Date.valueOf(endYear + MINUS_SIGN + endMonth + MINUS_SIGN + endDay);
            Long actualProjectId = Long.parseLong(projectId);
            Long actualExecutorId = Long.parseLong(executorId);
            if (endDate.after(startDate) || endDate.equals(startDate)){
                taskService.add(new TaskEntity(status,taskName, actualProjectId, work, startDate,endDate, actualExecutorId));
                response.sendRedirect(COMMAND_TASK_LIST);
            } else {
                request.setAttribute(VALIDATION_ERROR_PARAM_NAME, DATE_COLLISION);
                showTaskForm(request, response, CREATE_MODE);
            }
        } else {
            showTaskForm(request, response, CREATE_MODE);
        }
    }

    /**
     * Метод который получает из формы параметры задачи и по результатам проверки
     * либо редактирует конкретную и перенаправляет на страницу списка задач
     * либо возвращает на страницу формы
     * @param request запрос клиента
     * @param response ответ сервера
     * @throws IOException возникает в случае проблем с получением/записью данных
     * @throws ServletException если в работе сервлета возникают проблемы
     */
    private void editTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
        Long id = Long.parseLong(request.getParameter(ID_PARAM_NAME));

        if (validateFields(request, taskName, projectId, work,
                startYear, startMonth, startDay,
                endYear, endMonth, endDay,
                executorId, status))
        {
            Date startDate = Date.valueOf(startYear + MINUS_SIGN + startMonth + MINUS_SIGN + startDay);
            Date endDate = Date.valueOf(endYear + MINUS_SIGN + endMonth + MINUS_SIGN + endDay);
            Long actualProjectId = Long.parseLong(projectId);
            Long actualExecutorId = Long.parseLong(executorId);
            if (endDate.after(startDate) || endDate.equals(startDate)){
                taskService.update(new TaskEntity(status,taskName, actualProjectId, work, startDate,endDate, actualExecutorId, id));
                response.sendRedirect(COMMAND_TASK_LIST);
            } else {
                request.setAttribute(VALIDATION_ERROR_PARAM_NAME, DATE_COLLISION);
                showTaskForm(request, response, EDIT_MODE);
            }
        } else {
            showTaskForm(request, response, EDIT_MODE);
        }
    }

    /**
     * Метод который получает идентификатор задачи,
     * по которому удаляет конкретную задачу,
     * и перенаправляет на страницу списка задач для её обновления
     * @param request запрос клиента
     * @param response ответ сервера
     * @throws IOException возникает в случае проблем с получением/записью данных
     */
    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long taskId = Long.parseLong(request.getParameter(ID_PARAM_NAME));
        taskService.delete(taskId);
        response.sendRedirect(COMMAND_TASK_LIST);
    }

    /**
     * Метод который создаёт таблицу, в которой ключ это идентификатор работника,
     * а значение полное имя
     * @return таблица идентификаторов и имён работников
     */
    private HashMap<Long, String> getEmployeeNames() {
        final HashMap<Long, String> employeeNames = new HashMap<>();
        for (EmployeeEntity employee : employeeService.findAll()) {
            employeeNames.put(employee.getId(), employee.getLastName() + " " + employee.getFirstName());
        }
        return employeeNames;
    }

    /**
     * Метод который создаёт таблицу, в которой ключ это идентификатор проекта,
     * а значение наименование проекта
     * @return таблица идентификаторов и наименований проектов
     */
    private HashMap<Long, String> getProjectNames() {
        final HashMap<Long, String> projectNames = new HashMap<>();
        for (ProjectEntity project : projectService.findAll()) {
            projectNames.put(project.getId(), project.getName());
        }
        return projectNames;
    }

    /**
     * Метод который проверяет данные задач и записывает ошибки в запрос
     * @param request запрос клиента
     * @param taskName имя задачи
     * @param projectId идентификатор проекта
     * @param work часы работы над задачей
     * @param startYear год начала
     * @param startMonth месяц начала
     * @param startDay день начала
     * @param endYear год окончания
     * @param endMonth месяц окончания
     * @param endDay день окончания
     * @param executorId идентификатор работника
     * @param status статус задачи
     * @return false если данные не проходят проверку и
     * true если данные верны
     */
    private boolean validateFields(HttpServletRequest request, String taskName, String projectId, String work,
                                  String startYear, String startMonth, String startDay,
                                  String endYear, String endMonth, String endDay, String executorId, TaskStatus status) {

        DateValidator dateValidator = DateValidatorImpl.getInstance();

        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern(YEAR_FORMAT).withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern(DAY_FORMAT).withResolverStyle(ResolverStyle.STRICT);

        if (taskName == null || taskName.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, TASK_NAME_NULL);
            return false;
        }
        if (projectId == null || projectId.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, PROJECT_NULL);
            return false;
        }
        if (work == null || work.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WORK_NULL);
            return false;
        } else {
            try {
                Integer.parseInt(work);
            } catch (NumberFormatException nfe) {
                request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WORK_NOT_INTEGER);
                return false;
            }
            if (Integer.parseInt(work) < 0) {
                request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WORK_NEGATIVE);
                return false;
            }
        }
        if (startYear == null || startYear.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, START_YEAR_NULL);
            return false;
        } else if (!dateValidator.isValid(yearFormatter, startYear)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, INVALID_START_YEAR);
            return false;
        }
        if (startMonth == null || startMonth.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, START_MONTH_NULL);
            return false;
        }
        if (startDay == null || startDay.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, START_DAY_NULL);
            return false;
        } else if (!dateValidator.isValid(dayFormatter, startDay)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, INVALID_START_DAY);
            return false;
        }
        if (!isDateValid(dateValidator, startYear, startMonth, startDay)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WRONG_START_DATE);
            return false;
        }
        if (endYear == null || endYear.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, END_YEAR_NULL);
            return false;
        } else if (!dateValidator.isValid(yearFormatter, endYear)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, INVALID_END_YEAR);
            return false;
        }
        if (endMonth == null || endMonth.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, END_MONTH_NULL);
            return false;
        }
        if (endDay == null || endDay.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, END_DAY_NULL);
            return false;
        } else if (!dateValidator.isValid(dayFormatter, endDay)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, INVALID_END_DAY);
            return false;
        }
        if (!isDateValid(dateValidator, endYear, endMonth, endDay)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, WRONG_END_DATE);
            return false;
        }
        if (executorId == null || executorId.equals(EMPTY_STRING)) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, EXECUTOR_NULL);
            return false;
        }
        if (status == null) {
            request.setAttribute(VALIDATION_ERROR_PARAM_NAME, STATUS_NULL);
            return false;
        }
        return true;
    }

    /**
     * Метод который проверяет существует ли введённая дата
     * @param dateValidator заранее созданный валидатор даты
     * @param year проверяемый год
     * @param month проверяемый месяц
     * @param day проверяемый день
     * @return true если дата верна и false если даты не существует
     */
    static boolean isDateValid(DateValidator dateValidator, String year, String month, String day) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);

        return dateValidator.isValid(dateFormatter, year + MINUS_SIGN + month + MINUS_SIGN + day);
    }
}
