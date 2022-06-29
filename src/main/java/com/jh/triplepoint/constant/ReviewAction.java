package com.jh.triplepoint.constant;

import com.jh.triplepoint.exception.UnknownReviewActionException;

public enum ReviewAction {
    ADD, MOD, DELETE;

    public static ReviewAction of(String value) {
        try {
            return ReviewAction.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new UnknownReviewActionException();
        }
    }
}
