package io.hayk.demo.externalaccount;

import io.hayk.demo.AbstractDomainEntity;

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
}
