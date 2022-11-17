package com.qulix.shilomy.trainingtask.web.controller.task;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.web.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.web.service.EntityService;
import com.qulix.shilomy.trainingtask.web.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.web.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.web.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.web.validator.TaskValidator;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

/**
 * Класс HTTP сервлета, который отвечает за обработку запроса по редактированию задачи.
 */
@WebServlet("/editTask")
public class EditTaskController extends HttpServlet {
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    /**
     * Метод обработки GET запроса, который добавляет на страницу необходимые для отображения параметры,
     * а затем перенаправляет на неё.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                  полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws IOException возникает в случае проблем с получением строки для перенаправления.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskEntity task = taskService.get(Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME)));
        request.setAttribute(ControllerConstants.TASK_PARAM_NAME, task);
        request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME, ControllerConstants.EDIT_MODE);
        fillPage(request);
        request.getRequestDispatcher(ControllerConstants.EDIT_TASK_PAGE).forward(request, response);
    }

    /**
     * Метод обработки POST запроса, который получает данные из запроса, обновляет сущность в базе,
     * а потом перенаправляет на страницу со списком задач.
     * @param request   объект {@link HttpServletRequest} который хранит запрос клиента,
     *                  полученный от сервлета
     *
     * @param response  объект {@link HttpServletResponse} который хранит ответ,
     *                  отправляемый сервлетом клиенту
     *
     * @throws IOException возникает в случае проблем с получением строки для перенаправления
     * @throws ServletException если в работе сервлета возникают проблемы при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> errors = TaskValidator.isValid(request);
        if (errors.isEmpty()) {
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
            Long id = Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM_NAME));

            Date startDate = Date.valueOf(startYear + ControllerConstants.MINUS_SIGN + startMonth + ControllerConstants.MINUS_SIGN + startDay);
            Date endDate = Date.valueOf(endYear + ControllerConstants.MINUS_SIGN + endMonth + ControllerConstants.MINUS_SIGN + endDay);

            TaskEntity newTask = new TaskEntity(status, taskName, Long.parseLong(projectId), work, startDate, endDate, Long.parseLong(executorId), id);
            taskService.update(newTask);
            response.sendRedirect(ControllerConstants.COMMAND_TASK_LIST);
        } else {
            fillPage(request);
            request.setAttribute(ControllerConstants.ERROR_MESSAGES_PARAM, errors);
            request.getRequestDispatcher(ControllerConstants.EDIT_TASK_PAGE).forward(request, response);
        }

    }

    /**
     * Метод который заполняет страницу данными, которые необходимы для её корректной работы.
     *
     * @param request объект {@link ServletRequest} который хранит запрос клиента, полученный от сервлета
     */
    public void fillPage(HttpServletRequest request) {
        request.setAttribute(ControllerConstants.EMPLOYEES_PARAM_NAME, employeeService.findAll());
        request.setAttribute(ControllerConstants.PROJECTS_PARAM_NAME, projectService.findAll());
        request.setAttribute(ControllerConstants.STATUS_PARAM_NAME, TaskStatus.values());
        if (request.getParameter(ControllerConstants.PROJECT_LOCK_PARAM) != null) {
            ProjectEntity currentProject = projectService.get(Long.parseLong(request.getParameter(ControllerConstants.CURRENT_PROJECT_PARAM_NAME)));
            request.setAttribute(ControllerConstants.PROJECT_LOCK_PARAM, true);
            request.setAttribute(ControllerConstants.CURRENT_PROJECT_PARAM_NAME, currentProject);
        }
    }
}
