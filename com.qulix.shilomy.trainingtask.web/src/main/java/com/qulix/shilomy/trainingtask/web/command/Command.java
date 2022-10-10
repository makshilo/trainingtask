package com.qulix.shilomy.trainingtask.web.command;

import com.qulix.shilomy.trainingtask.web.controller.CommandRequest;
import com.qulix.shilomy.trainingtask.web.controller.CommandResponse;

public interface Command {
    CommandResponse execute(CommandRequest request);

    static Command of(String name) {
        return CommandRegistry.of(name);
    }
}
