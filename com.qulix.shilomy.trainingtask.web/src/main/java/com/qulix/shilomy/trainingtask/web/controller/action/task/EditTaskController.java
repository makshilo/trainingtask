package com.qulix.shilomy.trainingtask.web.controller.action.task;

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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

@WebServlet("/editTask")
public class EditTaskController extends HttpServlet {
    public static final String EMPLOYEES_PARAM_NAME = "employees";
    public static final String PROJECTS_PARAM_NAME = "projects";
    public static final String CURRENT_PROJECT_PARAM_NAME = "currentProject";
    public static final String PROJECT_LOCK_PARAM = "projectLock";
    public static final String TASK_STATUS_PARAM_NAME = "taskStatus";

    public static final String ID_PARAM_NAME = "id";
    public static final String PAGE_MODE_PARAM_NAME = "pageMode";
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
    public static final String WRONG_START_DATE = "Введённая дата не существует";
    public static final String END_YEAR_NULL = "Год окончания не заполнен";
    public static final String INVALID_END_YEAR = "Введённое значение не соответствует формату: гггг";
    public static final String END_MONTH_NULL = "Месяц окончания не выбран";
    public static final String END_DAY_NULL = "День окончания не заполнен";
    public static final String INVALID_END_DAY = "Введённое значение не соответствует формату: дд";
    public static final String WRONG_END_DATE = "Введённая дата не существует";
    public static final String DATE_COLLISION = "Дата начала больше даты окончания";
    public static final String EXECUTOR_NULL = "Исполнитель не выбран";
    public static final String STATUS_NULL = "Статус не выбран";


    private static final String EDIT_TASK_PAGE = "/jsp/editTask.jsp";
    private static final String COMMAND_TASK_LIST = "/tasks";

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProjectService projectService = (ProjectService) serviceFactory.serviceFor(ProjectEntity.class);
    private final EmployeeService employeeService = (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class);
    private final TaskService taskService = (TaskService) serviceFactory.serviceFor(TaskEntity.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
                request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
                fillPage(request);
                request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
            }
        } else {
            request.setAttribute(PAGE_MODE_PARAM_NAME, EDIT_MODE);
            fillPage(request);
            request.getRequestDispatcher(EDIT_TASK_PAGE).forward(request, response);
        }
    }

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

    static boolean isDateValid(DateValidator dateValidator, String year, String month, String day) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);

        return dateValidator.isValid(dateFormatter, year + MINUS_SIGN + month + MINUS_SIGN + day);
    }

    public void fillPage(HttpServletRequest request) {
        request.setAttribute(EMPLOYEES_PARAM_NAME, employeeService.findAll());
        request.setAttribute(PROJECTS_PARAM_NAME, projectService.findAll());
        request.setAttribute(TASK_STATUS_PARAM_NAME, TaskStatus.values());
        if (request.getParameter(PROJECT_LOCK_PARAM) != null) {
            ProjectEntity currentProject = projectService.get(Long.parseLong(request.getParameter(CURRENT_PROJECT_PARAM_NAME)));
            request.setAttribute(PROJECT_LOCK_PARAM, true);
            request.setAttribute(CURRENT_PROJECT_PARAM_NAME, currentProject);
        }
    }
}
