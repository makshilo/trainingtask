package com.qulix.shilomy.trainingtask.web.command;

import com.qulix.shilomy.trainingtask.web.command.impl.CommandFactoryImpl;

public interface CommandFactory {
    Command createCommand(String name);
    static CommandFactory getInstance() {
        return CommandFactoryImpl.getInstance();
    }
}
