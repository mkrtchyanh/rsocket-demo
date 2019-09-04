package io.hayk.demo.note.user;

public class GetUserIdByExternalAccountUidRequest {

    private String externalAccountUid;

    public GetUserIdByExternalAccountUidRequest() {
        super();
    }

    public GetUserIdByExternalAccountUidRequest(final String externalAccountUid) {
        this.externalAccountUid = externalAccountUid;
    }

    public String getExternalAccountUid() {
        return externalAccountUid;
    }

    public void setExternalAccountUid(String externalAccountUid) {
        this.externalAccountUid = externalAccountUid;
    }
}
