package io.hayk.rsocketdemo;

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
