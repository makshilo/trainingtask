package com.qulix.shilomy.trainingtask.web.controller.task;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstants;
import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeFormParams;
import com.qulix.shilomy.trainingtask.web.controller.project.ProjectFormParams;
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
        TaskEntity task = taskService.get(Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM.get())));
        request.setAttribute(TaskFormParams.TASK_PARAM.get(), task);
        request.setAttribute(ControllerConstants.PAGE_MODE_PARAM_NAME.get(), ControllerConstants.EDIT_MODE.get());
        fillPage(request);
        request.getRequestDispatcher(ControllerConstants.EDIT_TASK_PAGE.get()).forward(request, response);
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
            Long id = Long.parseLong(request.getParameter(ControllerConstants.ID_PARAM.get()));

            Date startDate = Date.valueOf(startYear + ControllerConstants.MINUS_SIGN + startMonth + ControllerConstants.MINUS_SIGN + startDay);
            Date endDate = Date.valueOf(endYear + ControllerConstants.MINUS_SIGN + endMonth + ControllerConstants.MINUS_SIGN + endDay);

            TaskEntity newTask = new TaskEntity(status, taskName, Long.parseLong(projectId), work, startDate, endDate, Long.parseLong(executorId), id);
            taskService.update(newTask);
            response.sendRedirect(ControllerConstants.TASK_LIST.get());
        } else {
            fillPage(request);
            request.setAttribute(ControllerConstants.ERROR_MESSAGES_PARAM.get(), errors);
            request.getRequestDispatcher(ControllerConstants.EDIT_TASK_PAGE.get()).forward(request, response);
        }

    }

    /**
     * Метод который заполняет страницу данными, которые необходимы для её корректной работы.
     *
     * @param request объект {@link ServletRequest} который хранит запрос клиента, полученный от сервлета
     */
    public void fillPage(HttpServletRequest request) {
        request.setAttribute(EmployeeFormParams.EMPLOYEES_PARAM.get(), employeeService.findAll());
        request.setAttribute(ProjectFormParams.PROJECTS_PARAM.get(), projectService.findAll());
        request.setAttribute(TaskFormParams.STATUS_PARAM.get(), TaskStatus.values());
        if (request.getParameter(ControllerConstants.PROJECT_LOCK_PARAM.get()) != null) {
            ProjectEntity currentProject = projectService.get(Long.parseLong(request.getParameter(ControllerConstants.CURRENT_PROJECT_PARAM.get())));
            request.setAttribute(ControllerConstants.PROJECT_LOCK_PARAM.get(), true);
            request.setAttribute(ControllerConstants.CURRENT_PROJECT_PARAM.get(), currentProject);
        }
    }
}
