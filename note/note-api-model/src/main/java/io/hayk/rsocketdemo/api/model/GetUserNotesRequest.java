package io.hayk.rsocketdemo.api.model;

public class GetUserNotesRequest {

    private Long userId;

    public GetUserNotesRequest() {
        super();
    }

    public GetUserNotesRequest(final Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
