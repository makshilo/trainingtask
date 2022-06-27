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
import com.qulix.shilomy.trainingtask.web.command.impl.page.project.ShowProjectNameBusyPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowCreateTaskPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowEditTaskPage;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowEditTaskPageFromProjects;
import com.qulix.shilomy.trainingtask.web.command.impl.page.task.ShowTaskListPage;
import com.qulix.shilomy.trainingtask.web.controller.PropertyContext;
import com.qulix.shilomy.trainingtask.web.controller.RequestFactory;

public class CommandFactoryImpl implements CommandFactory {
    private final RequestFactory requestFactory = RequestFactory.getInstance();
    private final PropertyContext propertyContext = PropertyContext.getInstance();

    private CommandFactoryImpl() {
    }

    @Override
    public Command createCommand(String name) {
        switch (name) {
            case "mainPage":
                return ShowMainPage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "projectsPage":
                return ShowProjectListPage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "projectCreatePage":
                return ShowCreateProjectPage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "createProject":
                return CreateProject.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "projectNameBusy":
                return ShowProjectNameBusyPage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "projectEditPage":
                return ShowEditProjectPage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "editProject":
                return EditProject.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "deleteProject":
                return DeleteProject.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "tasksPage":
                return ShowTaskListPage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "taskCreatePage":
                return ShowCreateTaskPage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "createTask":
                return CreateTask.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "taskEditPage":
                return ShowEditTaskPage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "taskEditFromProjectPage":
                return ShowEditTaskPageFromProjects.getInstance(
                    requestFactory,
                    propertyContext
            );
            case "editTask":
                return EditTask.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "deleteTask":
                return DeleteTask.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "employeesPage":
                return ShowEmployeeListPage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "employeeCreatePage":
                return ShowCreateEmployeePage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "createEmployee":
                return CreateEmployee.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "employeeEditPage":
                return ShowEditEmployeePage.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "editEmployee":
                return EditEmployee.getInstance(
                        requestFactory,
                        propertyContext
                );
            case "deleteEmployee":
                return DeleteEmployee.getInstance(
                        requestFactory,
                        propertyContext
                );
            default:
                return NotFoundErrorPage.getInstance(requestFactory, propertyContext);
        }
    }

    public static CommandFactoryImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CommandFactoryImpl INSTANCE = new CommandFactoryImpl();
    }
}
