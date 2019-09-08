package io.hayk.rsocketdemo.note.user;


import io.hayk.rsocketdemo.note.common.AbstractResult;
import io.hayk.rsocketdemo.note.common.ApplicationError;

import java.util.List;

public class BindExternalAccountResult extends AbstractResult {

    private Long userId;

    public BindExternalAccountResult() {
        super();
    }

    public BindExternalAccountResult(final Long userId) {
        this.userId = userId;
    }

    public BindExternalAccountResult(final List<? extends ApplicationError> errors) {
        super(errors);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
