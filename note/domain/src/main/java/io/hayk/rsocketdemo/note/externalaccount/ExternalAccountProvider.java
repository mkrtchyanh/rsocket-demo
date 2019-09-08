package io.hayk.rsocketdemo.note.externalaccount;

import io.hayk.rsocketdemo.note.common.AbstractDomainEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "external_account_provider",
        uniqueConstraints = @UniqueConstraint(name = "UQ_external_account_provider_name", columnNames = "name"))
public class ExternalAccountProvider extends AbstractDomainEntity {

    @Column(name = "name", unique = true, updatable = false)
    private String name;

    /**
     * Accessible by reflection.
     * <p>
     * Will be used by frameworks depending on default constructor
     */
    ExternalAccountProvider() {
        super();
    }

    public ExternalAccountProvider(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ExternalAccountProvider provider = (ExternalAccountProvider) o;
        return new EqualsBuilder()
                .append(name, provider.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }
}
