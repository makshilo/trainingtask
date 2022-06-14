package com.qulix.shilomy.trainingtask.web.command;

public enum CommandRegistry {
    MAIN_PAGE( "main_page"),
    SHOW_BIKES("show_bikes"),
    ERROR( "show_error"),
    LOGOUT("logout"),
    DEFAULT( "");

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
