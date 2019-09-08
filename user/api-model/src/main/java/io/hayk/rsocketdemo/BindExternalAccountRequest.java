package io.hayk.rsocketdemo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BindExternalAccountRequest {

    private String email;

    private String externalAccountUid;

    private String providerName;

    public BindExternalAccountRequest() {
        super();
    }

    public BindExternalAccountRequest(final String email, final String externalAccountUid, final String providerName) {
        this.email = email;
        this.externalAccountUid = externalAccountUid;
        this.providerName = providerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BindExternalAccountRequest that = (BindExternalAccountRequest) o;
        return new EqualsBuilder()
                .append(email, that.email)
                .append(externalAccountUid, that.externalAccountUid)
                .append(providerName, that.providerName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(email)
                .append(externalAccountUid)
                .append(providerName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .append("externalAccountUid", externalAccountUid)
                .append("providerName", providerName)
                .toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
