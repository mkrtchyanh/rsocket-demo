package io.hayk.demo.note.user;

import io.hayk.demo.common.ApplicationError;

import java.util.List;

public class GetUserIdByExternalAccountUidResult {

    private Long userId;

    private List<? extends ApplicationError> errors;

    public GetUserIdByExternalAccountUidResult() {
        super();
    }

    public GetUserIdByExternalAccountUidResult(final Long userId) {
        this.userId = userId;
    }

    public GetUserIdByExternalAccountUidResult(final List<? extends ApplicationError> errors) {
        this.errors = errors;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<? extends ApplicationError> getErrors() {
        return errors;
    }

    public void setErrors(List<? extends ApplicationError> errors) {
        this.errors = errors;
    }
}
