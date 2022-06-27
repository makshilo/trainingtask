package com.qulix.shilomy.trainingtask.web.exception;

import java.sql.SQLException;

public class CouldNotInitialiseConnectionService extends Throwable {
    public CouldNotInitialiseConnectionService(String message, Throwable cause) {
        super(message, cause);
    }
}
