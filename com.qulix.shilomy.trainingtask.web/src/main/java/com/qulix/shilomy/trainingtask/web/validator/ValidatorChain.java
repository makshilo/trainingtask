package com.qulix.shilomy.trainingtask.web.validator;

import javax.servlet.http.HttpServletRequest;

/**
 * Цепочка валидаторов
 */
public abstract class ValidatorChain {
    /**
     * Следующий в цепи
     */
    private ValidatorChain next;

    /**
     * Связывание валидаторов в цепочку
     *
     * @param first первый в цепи
     * @param chain цепочка
     * @return первый в цепи
     */
    public static ValidatorChain link(ValidatorChain first, ValidatorChain... chain) {
        ValidatorChain head = first;
        for (ValidatorChain nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * Описание метода проверки
     *
     * @param req запрос
     * @return результат валидации
     */
    public abstract boolean check(HttpServletRequest req);

    /**
     * Вызов проверки на следующем в цепи
     *
     * @param req запрос
     * @return если последний в цепи true, иначе результат следующего в цепи
     */
    protected boolean checkNext(HttpServletRequest req) {
        if (next == null) {
            return true;
        }
        return next.check(req);
    }
}
