package io.hayk.rsocketdemo.api.model;


public class BindExternalAccountResult {

    private Long userId;

    public BindExternalAccountResult() {
        super();
    }

    public BindExternalAccountResult(final Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
