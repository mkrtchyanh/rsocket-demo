package io.hayk.rsocketdemo.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

final class ImmutableBindExternalAccountParams implements BindExternalAccountParam {

    private final String email;

    private final String externalAccountUid;

    private final String providerName;

    ImmutableBindExternalAccountParams(final String email, final String externalAccountUid, final String providerName) {
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
        final BindExternalAccountParam that = (BindExternalAccountParam) o;
        return new EqualsBuilder()
                .append(email, that.email())
                .append(externalAccountUid, that.externalAccountUid())
                .append(providerName, that.providerName())
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
    public String email() {
        return email;
    }

    @Override
    public String externalAccountUid() {
        return externalAccountUid;
    }

    @Override
    public String providerName() {
        return providerName;
    }
}
