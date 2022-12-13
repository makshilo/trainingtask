package com.qulix.shilomy.trainingtask.web.validator;

import javax.servlet.http.HttpServletRequest;

public abstract class ValidatorChain {
    private ValidatorChain next;

    public static ValidatorChain link(ValidatorChain first, ValidatorChain... chain) {
        ValidatorChain head = first;
        for (ValidatorChain nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract boolean check(HttpServletRequest req);

    protected boolean checkNext(HttpServletRequest req) {
        if (next == null) {
            return true;
        }
        return next.check(req);
    }
}
