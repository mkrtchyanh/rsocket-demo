package io.hayk.rsocketdemo.note.user;

import io.hayk.rsocketdemo.note.common.ApplicationError;

import java.util.List;
import java.util.stream.Collectors;

public class GetUserIdByExternalAccountUidResult {

    private Long userId;

    private List<String> errors;

    public GetUserIdByExternalAccountUidResult() {
        super();
    }

    public GetUserIdByExternalAccountUidResult(final Long userId) {
        this.userId = userId;
    }

    public GetUserIdByExternalAccountUidResult(final List<? extends ApplicationError> errors) {
        this.errors = errors.stream()
                .map(ApplicationError::textValue)
                .collect(Collectors.toList());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
