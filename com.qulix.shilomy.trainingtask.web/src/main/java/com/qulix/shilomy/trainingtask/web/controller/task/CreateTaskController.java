package com.qulix.shilomy.trainingtask.web.controller.task;

import com.qulix.shilomy.trainingtask.data.dao.impl.EmployeeDao;
import com.qulix.shilomy.trainingtask.data.dao.impl.ProjectDao;
import com.qulix.shilomy.trainingtask.data.dao.impl.TaskDao;
import com.qulix.shilomy.trainingtask.data.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.data.entity.impl.TaskStatus;
import com.qulix.shilomy.trainingtask.data.param.TaskParam;
import com.qulix.shilomy.trainingtask.data.service.EntityService;
import com.qulix.shilomy.trainingtask.data.service.impl.EmployeeServiceImpl;
import com.qulix.shilomy.trainingtask.data.service.impl.ProjectServiceImpl;
import com.qulix.shilomy.trainingtask.data.service.impl.TaskServiceImpl;
import com.qulix.shilomy.trainingtask.data.param.EmployeeParam;
import com.qulix.shilomy.trainingtask.data.param.ProjectParam;
import com.qulix.shilomy.trainingtask.web.validator.TaskValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

/**
 * Сервлет обрабатывающий запросы по созданию задачи
 */
@WebServlet("/createTask")
public class CreateTaskController extends HttpServlet {

    //Сервис работы с проектами
    private final EntityService<ProjectEntity> projectService = ProjectServiceImpl.getInstance(ProjectDao.getInstance());

    //Сервис работы с сотрудниками
    private final EntityService<EmployeeEntity> employeeService = EmployeeServiceImpl.getInstance(EmployeeDao.getInstance());

    //Сервис работы с задачами
    private final EntityService<TaskEntity> taskService = TaskServiceImpl.getInstance(TaskDao.getInstance());

    /**
     * Обработка GET запроса перенаправления на форму задачи
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(TaskParam.PAGE_MODE.get(), TaskParam.CREATE.get());
        fillPage(request);
        request.getRequestDispatcher(TaskParam.EDIT_TASK_PAGE.get()).forward(request, response);
    }

    /**
     * Обработка POST запроса создания задачи
     *
     * @param request  {@link HttpServletRequest} запрос
     * @param response {@link HttpServletResponse} ответ
     * @throws IOException      ошибка получения строки для перенаправления
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

            Date startDate = Date.valueOf(String.join(TaskParam.MINUS.get(), startYear, startMonth, startDay));
            Date endDate = Date.valueOf(String.join(TaskParam.MINUS.get(), endYear, endMonth, endDay));

            TaskEntity newTask = new TaskEntity(status, taskName, Long.parseLong(projectId), work, startDate, endDate, Long.parseLong(executorId));

            taskService.add(newTask);
            response.sendRedirect(TaskParam.TASK_LIST.get());
        } else {
            fillPage(request);
            request.getRequestDispatcher(TaskParam.EDIT_TASK_PAGE.get()).forward(request, response);
        }
    }

    /**
     * Добавление в запрос данных для отображения на странице
     * @param request запрос
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
