package io.hayk.demo.note.user;


import io.hayk.demo.common.ApplicationError;

import java.util.Collections;
import java.util.List;

public class BindExternalAccountResponse {

    private Long userId;

    private List<? extends ApplicationError> errors;

    public BindExternalAccountResponse() {
        super();
    }

    public BindExternalAccountResponse(final Long userId) {
        this.userId = userId;
    }


    public <E extends ApplicationError> BindExternalAccountResponse(final List<? extends ApplicationError> errors) {
        this.errors = errors;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<? extends ApplicationError> getErrors() {
        return errors == null ? Collections.emptyList() : errors;
    }

    public void setErrors(List<? extends ApplicationError> errors) {
        this.errors = errors;
    }
}
