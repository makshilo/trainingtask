package com.qulix.shilomy.trainingtask.web.command.impl;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.command.CommandFactory;
import com.qulix.shilomy.trainingtask.web.command.impl.action.employee.CreateEmployee;
import com.qulix.shilomy.trainingtask.web.command.impl.action.employee.DeleteEmployee;
import com.qulix.shilomy.trainingtask.web.command.impl.action.employee.EditEmployee;
import com.qulix.shilomy.trainingtask.web.command.impl.action.project.CreateProject;
import com.qulix.shilomy.trainingtask.web.command.impl.action.project.DeleteProject;
import com.qulix.shilomy.trainingtask.web.command.impl.action.project.EditProject;
import com.qulix.shilomy.trainingtask.web.command.impl.action.task.CreateTask;
import com.qulix.shilomy.trainingtask.web.command.impl.action.task.DeleteTask;
import com.qulix.shilomy.trainingtask.web.command.impl.action.task.EditTask;
import com.qulix.shilomy.trainingtask.web.command.impl.page.*;
import com.qulix.shilomy.trainingtask.web.command.impl.page.employee.ShowCreateEmployeePage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.employee.ShowEditEmployeePage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.employee.ShowEmployeeListPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.project.ShowCreateProjectPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.project.ShowEditProjectPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.project.ShowProjectListPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowCreateTaskPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowEditTaskPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowTaskListPage;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;
import com.qulix.shilomy.trainingtask.web.entity.impl.EmployeeEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.ProjectEntity;
import com.qulix.shilomy.trainingtask.web.entity.impl.TaskEntity;
import com.qulix.shilomy.trainingtask.web.service.EmployeeService;
import com.qulix.shilomy.trainingtask.web.service.ProjectService;
import com.qulix.shilomy.trainingtask.web.service.ServiceFactory;
import com.qulix.shilomy.trainingtask.web.service.TaskService;

public class CommandFactoryImpl implements CommandFactory {
    private final RequestFactory requestFactory = RequestFactory.getInstance();
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private CommandFactoryImpl() {
    }

    @Override
    public Command createCommand(String name) {
        switch (name) {
            case "mainPage":
                return ShowMainPage.getInstance(
                        requestFactory
                );
            case "projectsPage":
                return ShowProjectListPage.getInstance(
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        requestFactory
                );
            case "projectCreatePage":
                return ShowCreateProjectPage.getInstance(
                        requestFactory
                );
            case "createProject":
                return CreateProject.getInstance(
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        requestFactory
                );
            case "projectEditPage":
                return ShowEditProjectPage.getInstance(
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        (TaskService) serviceFactory.serviceFor(TaskEntity.class),
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        requestFactory
                );
            case "editProject":
                return EditProject.getInstance(
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        requestFactory
                );
            case "deleteProject":
                return DeleteProject.getInstance(
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        requestFactory
                );
            case "tasksPage":
                return ShowTaskListPage.getInstance(
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        (TaskService) serviceFactory.serviceFor(TaskEntity.class),
                        requestFactory
                );
            case "taskCreatePage":
                return ShowCreateTaskPage.getInstance(
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        requestFactory
                );
            case "createTask":
                return CreateTask.getInstance(
                        (TaskService) serviceFactory.serviceFor(TaskEntity.class),
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        requestFactory
                );
            case "taskEditPage":
                return ShowEditTaskPage.getInstance(
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        (TaskService) serviceFactory.serviceFor(TaskEntity.class),
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        requestFactory
                );
            case "editTask":
                return EditTask.getInstance(
                        (TaskService) serviceFactory.serviceFor(TaskEntity.class),
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        (ProjectService) serviceFactory.serviceFor(ProjectEntity.class),
                        requestFactory
                );
            case "deleteTask":
                return DeleteTask.getInstance(
                        (TaskService) serviceFactory.serviceFor(TaskEntity.class),
                        requestFactory
                );
            case "employeesPage":
                return ShowEmployeeListPage.getInstance(
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        requestFactory
                );
            case "employeeCreatePage":
                return ShowCreateEmployeePage.getInstance(
                        requestFactory
                );
            case "createEmployee":
                return CreateEmployee.getInstance(
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        requestFactory
                );
            case "employeeEditPage":
                return ShowEditEmployeePage.getInstance(
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        requestFactory
                );
            case "editEmployee":
                return EditEmployee.getInstance(
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        requestFactory
                );
            case "deleteEmployee":
                return DeleteEmployee.getInstance(
                        (EmployeeService) serviceFactory.serviceFor(EmployeeEntity.class),
                        requestFactory
                );
            default:
                return NotFoundErrorPage.getInstance(requestFactory);
        }
    }

    public static CommandFactoryImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CommandFactoryImpl INSTANCE = new CommandFactoryImpl();
    }
}
