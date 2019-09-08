package io.hayk.rsocketdemo.note.user;

public class GetUserIdByExternalAccountRequest {

    private String externalAccountUid;

    private String providerName;

    public GetUserIdByExternalAccountRequest() {
        super();
    }

    public GetUserIdByExternalAccountRequest(final String externalAccountUid, final String providerName) {
        this.externalAccountUid = externalAccountUid;
        this.providerName = providerName;
    }

    public String getExternalAccountUid() {
        return externalAccountUid;
    }

    public void setExternalAccountUid(String externalAccountUid) {
        this.externalAccountUid = externalAccountUid;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
