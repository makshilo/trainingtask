package com.qulix.shilomy.trainingtask.web.controller.task;

import com.qulix.shilomy.trainingtask.web.controller.employee.EmployeeParam;
import com.qulix.shilomy.trainingtask.web.controller.project.ProjectParam;
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
 * Сервлет обрабатывающий запросы по редактированию задачи
 */
@WebServlet("/editTask")
public class EditTaskController extends HttpServlet {
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    /**
     * Обработка GET запроса перенаправления на форму задачи для редактирования
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskEntity task = taskService.get(Long.parseLong(request.getParameter(TaskParam.ID.get())));
        request.setAttribute(TaskParam.TASK.get(), task);
        request.setAttribute(TaskParam.PAGE_MODE.get(), TaskParam.EDIT.get());
        fillPage(request);
        request.getRequestDispatcher(TaskParam.EDIT_TASK_PAGE.get()).forward(request, response);
    }

    /**
     * Обработка POST запроса изменения задачи
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошибка получения строки для перенаправления
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (TaskValidator.isValid(request)) {
            TaskStatus status = TaskStatus.of(request.getParameter(TaskParam.STATUS.get()));
            String taskName = request.getParameter(TaskParam.TASK_NAME.get());
            String projectId = request.getParameter(ProjectParam.PROJECT.get());
            String work = request.getParameter(TaskParam.WORK.get());
            String startYear = request.getParameter(TaskParam.START_YEAR.get());
            String startMonth = request.getParameter(TaskParam.START_MONTH.get());
            String startDay = request.getParameter(TaskParam.START_DAY.get());
            String endYear = request.getParameter(TaskParam.END_YEAR.get());
            String endMonth = request.getParameter(TaskParam.END_MONTH.get());
            String endDay = request.getParameter(TaskParam.END_DAY.get());
            String executorId = request.getParameter(TaskParam.EXECUTOR.get());
            Long id = Long.parseLong(request.getParameter(TaskParam.ID.get()));

            Date startDate = Date.valueOf(String.join(TaskParam.MINUS.get(), startYear, startMonth, startDay));
            Date endDate = Date.valueOf(String.join(TaskParam.MINUS.get(), endYear, endMonth, endDay));

            TaskEntity newTask = new TaskEntity(status, taskName, Long.parseLong(projectId), work, startDate, endDate, Long.parseLong(executorId), id);
            taskService.update(newTask);
            response.sendRedirect(TaskParam.TASK_LIST.get());
        } else {
            fillPage(request);
            request.getRequestDispatcher(TaskParam.EDIT_TASK_PAGE.get()).forward(request, response);
        }

    }

    /**
     * Заполнение страницы необходимыми данными
     *
     * @param request объект {@link ServletRequest} который хранит запрос клиента, полученный от сервлета
     */
    public void fillPage(HttpServletRequest request) {
        request.setAttribute(EmployeeParam.EMPLOYEES.get(), employeeService.findAll());
        request.setAttribute(ProjectParam.PROJECTS.get(), projectService.findAll());
        request.setAttribute(TaskParam.STATUS.get(), TaskStatus.values());
        if (request.getParameter(TaskParam.PROJECT_LOCK.get()) != null) {
            ProjectEntity currentProject = projectService.get(Long.parseLong(request.getParameter(TaskParam.CURRENT_PROJECT.get())));
            request.setAttribute(TaskParam.PROJECT_LOCK.get(), true);
            request.setAttribute(TaskParam.CURRENT_PROJECT.get(), currentProject);
        }
    }
}