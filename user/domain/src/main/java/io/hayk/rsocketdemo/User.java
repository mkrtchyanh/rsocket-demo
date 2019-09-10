package io.hayk.rsocketdemo;


import io.hayk.rsocketdemo.common.AbstractDomainEntity;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccount;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccountProvider;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_users_email", columnNames = "email")
})
@Access(AccessType.FIELD)
public class User extends AbstractDomainEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<ExternalAccount> externalAccounts;

    /**
     * Accessible by reflection.
     * <p>
     * Will be used by frameworks depending on default constructor
     */
    User() {
        super();
    }

    public User(final String email) {
        this.email = email;
        this.lastUpdated = LocalDateTime.now();
    }

    /**
     * Returns external account bound to this user.
     * Binds the external account to this user if it is not already bound.
     *
     * @param externalAccountUid the uuid of external account
     * @param provider           the provider of external account.
     */
    public ExternalAccount bindExternalAccount(final String externalAccountUid, final ExternalAccountProvider provider) {
        return findExternalAccountWithUidAndProvider(externalAccountUid, provider.getName())
                .orElseGet(() -> addExternalAccount(externalAccountUid, provider));
    }

    private ExternalAccount addExternalAccount(final String uid, final ExternalAccountProvider provider) {
        final ExternalAccount externalAccount = new ExternalAccount(uid, this, provider);
        initializedExternalAccounts().add(externalAccount);
        lastUpdated = LocalDateTime.now();
        return externalAccount;
    }


    private Optional<ExternalAccount> findExternalAccountWithUidAndProvider(final String uid, final String providerName) {
        return getExternalAccounts().stream()
                .filter(externalAccount -> externalAccount.isSameExternalAccount(uid, providerName))
                .findAny();
    }

    private List<ExternalAccount> initializedExternalAccounts() {
        if (externalAccounts == null) {
            externalAccounts = new ArrayList<>();
        }
        return externalAccounts;
    }

    /**
     * Returns unmodifiable list of external accounts
     */
    public List<ExternalAccount> getExternalAccounts() {
        return externalAccounts == null ? Collections.emptyList() : Collections.unmodifiableList(externalAccounts);
    }

    public String getEmail() {
        return email;
    }

    public void changeEmail(final String newEmail) {
        if (Objects.equals(email, newEmail)) {
            lastUpdated = LocalDateTime.now();
        }
        this.email = newEmail;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
