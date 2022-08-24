package com.qulix.shilomy.trainingtask.web.command;

public enum CommandRegistry {
    MAIN_PAGE( "mainPage"),
    PROJECTS_PAGE( "projectsPage"),
    CREATE_PROJECT_PAGE( "projectCreatePage"),
    CREATE_PROJECT("createProject"),
    EDIT_PROJECT_PAGE( "projectEditPage"),
    EDIT_PROJECT("editProject"),
    DELETE_PROJECT("deleteProject"),
    TASKS_PAGE( "tasksPage"),
    CREATE_TASK_PAGE( "taskCreatePage"),
    CREATE_TASK("createTask"),
    EDIT_TASK_PAGE( "taskEditPage"),
    EDIT_TASK("editTask"),
    EDIT_TASK_FP("taskEditFromProjectPage"),
    DELETE_TASK("deleteTask"),
    EMPLOYEES_PAGE( "employeesPage"),
    CREATE_EMPLOYEE_PAGE("employeeCreatePage"),
    CREATE_EMPLOYEE("createEmployee"),
    EDIT_EMPLOYEE_PAGE("employeeEditPage"),
    EDIT_EMPLOYEE("editEmployee"),
    DELETE_EMPLOYEE( "deleteEmployee"),
    DEFAULT( "mainPage");

    private final Command command;
    private final String path;

    CommandRegistry(String path) {
        this.command = CommandFactory.getInstance().createCommand(path);
        this.path = path;
    }

    public Command getCommand() {
        return command;
    }

    static Command of(String name) {
        for (CommandRegistry constant : values()) {
            if (constant.path.equalsIgnoreCase(name)) {
                return constant.command;
            }
        }
        return DEFAULT.command;
    }
}
