package io.hayk.demo.user;


import io.hayk.demo.externalaccount.ExternalAccount;
import io.hayk.demo.externalaccount.ExternalAccountProvider;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "note_user", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_user_email", columnNames = "email")
})
@Access(AccessType.FIELD)
public class User {

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
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
}
