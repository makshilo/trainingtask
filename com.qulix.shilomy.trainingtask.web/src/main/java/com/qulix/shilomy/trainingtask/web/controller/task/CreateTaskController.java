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
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошибка получения строки для перенаправления
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ControllerConstant.PAGE_MODE_PARAM_NAME.get(), ControllerConstant.CREATE_MODE.get());
        fillPage(request);
        request.getRequestDispatcher(ControllerConstant.EDIT_TASK_PAGE.get()).forward(request, response);
    }

    /**
     * Обработка POST запроса создания задачи
     * @param request   объект {@link HttpServletRequest} запрос клиента
     *
     * @param response  объект {@link HttpServletResponse} ответ сервлета
     *
     * @throws IOException ошибка получения строки для перенаправления
     * @throws ServletException ошибка сервлета при перенаправлении
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(TaskValidator.isValid(request)) {
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

            Date startDate = Date.valueOf(String.join(ControllerConstant.MINUS_SIGN.get(), startYear, startMonth, startDay));
            Date endDate = Date.valueOf(String.join(ControllerConstant.MINUS_SIGN.get(), endYear, endMonth, endDay));

            TaskEntity newTask = new TaskEntity(status, taskName, Long.parseLong(projectId), work, startDate, endDate, Long.parseLong(executorId));

            taskService.add(newTask);
            response.sendRedirect(ControllerConstant.TASK_LIST.get());
        } else {
            fillPage(request);
            request.getRequestDispatcher(ControllerConstant.EDIT_TASK_PAGE.get()).forward(request, response);
        }
    }

    /**
     * Заполнение страницы необходимыми данными
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
