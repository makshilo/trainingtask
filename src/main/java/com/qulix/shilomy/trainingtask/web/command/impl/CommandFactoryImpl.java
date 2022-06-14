package com.qulix.shilomy.trainingtask.web.command.impl;

import com.qulix.shilomy.trainingtask.web.command.Command;
import com.qulix.shilomy.trainingtask.web.command.CommandFactory;

public class CommandFactoryImpl implements CommandFactory {
    private static CommandFactoryImpl instance;

    private CommandFactoryImpl() {
    }

    @Override
    public Command createCommand(String name) {
        return null;
    }

    public static CommandFactoryImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CommandFactoryImpl INSTANCE = new CommandFactoryImpl();
    }
}
