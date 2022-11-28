package com.qulix.shilomy.trainingtask.web.controller.task;

import com.qulix.shilomy.trainingtask.web.controller.ControllerConstant;
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
        TaskEntity task = taskService.get(Long.parseLong(request.getParameter(ControllerConstant.ID_PARAM.get())));
        request.setAttribute(ControllerConstant.TASK_PARAM.get(), task);
        request.setAttribute(ControllerConstant.PAGE_MODE_PARAM_NAME.get(), ControllerConstant.EDIT_MODE.get());
        fillPage(request);
        request.getRequestDispatcher(ControllerConstant.EDIT_TASK_PAGE.get()).forward(request, response);
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
        if (TaskValidator.isValid(request)) {
            TaskStatus status = TaskStatus.of(request.getParameter(TaskFormParam.STATUS_PARAM.get()));
            String taskName = request.getParameter(TaskFormParam.TASK_NAME.get());
            String projectId = request.getParameter(ControllerConstant.PROJECT_PARAM.get());
            String work = request.getParameter(TaskFormParam.WORK_PARAM.get());
            String startYear = request.getParameter(TaskFormParam.START_YEAR_PARAM.get());
            String startMonth = request.getParameter(TaskFormParam.START_MONTH_PARAM.get());
            String startDay = request.getParameter(TaskFormParam.START_DAY_PARAM.get());
            String endYear = request.getParameter(TaskFormParam.END_YEAR_PARAM.get());
            String endMonth = request.getParameter(TaskFormParam.END_MONTH_PARAM.get());
            String endDay = request.getParameter(TaskFormParam.END_DAY_PARAM.get());
            String executorId = request.getParameter(TaskFormParam.EXECUTOR_PARAM.get());
            Long id = Long.parseLong(request.getParameter(ControllerConstant.ID_PARAM.get()));

            Date startDate = Date.valueOf(String.join(ControllerConstant.MINUS_SIGN.get(), startYear, startMonth, startDay));
            Date endDate = Date.valueOf(String.join(ControllerConstant.MINUS_SIGN.get(), endYear, endMonth, endDay));

            TaskEntity newTask = new TaskEntity(status, taskName, Long.parseLong(projectId), work, startDate, endDate, Long.parseLong(executorId), id);
            taskService.update(newTask);
            response.sendRedirect(ControllerConstant.TASK_LIST.get());
        } else {
            fillPage(request);
            request.getRequestDispatcher(ControllerConstant.EDIT_TASK_PAGE.get()).forward(request, response);
        }

    }

    /**
     * Метод который заполняет страницу данными, которые необходимы для её корректной работы.
     *
     * @param request объект {@link ServletRequest} который хранит запрос клиента, полученный от сервлета
     */
    public void fillPage(HttpServletRequest request) {
        request.setAttribute(ControllerConstant.EMPLOYEES_PARAM.get(), employeeService.findAll());
        request.setAttribute(ControllerConstant.PROJECTS_PARAM.get(), projectService.findAll());
        request.setAttribute(TaskFormParam.STATUS_PARAM.get(), TaskStatus.values());
        if (request.getParameter(ControllerConstant.PROJECT_LOCK_PARAM.get()) != null) {
            ProjectEntity currentProject = projectService.get(Long.parseLong(request.getParameter(ControllerConstant.CURRENT_PROJECT_PARAM.get())));
            request.setAttribute(ControllerConstant.PROJECT_LOCK_PARAM.get(), true);
            request.setAttribute(ControllerConstant.CURRENT_PROJECT_PARAM.get(), currentProject);
        }
    }
}
