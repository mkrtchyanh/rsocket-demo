package io.hayk.rsocketdemo.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

final class ImmutablBindExternalAccountResult implements BindExternalAccountResult{

    private final Long userId;

    ImmutablBindExternalAccountResult(final Long userId) {
        this.userId = userId;
    }

    @Override
    public Long userId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
       final ImmutablBindExternalAccountResult that = (ImmutablBindExternalAccountResult) o;
        return new EqualsBuilder()
                .append(userId, that.userId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .toString();
    }
}
