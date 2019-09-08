package io.hayk.rsocketdemo;

import io.hayk.rsocketdemo.externalaccount.ExternalAccount;

import java.util.Optional;

public interface UserService {

    ExternalAccount bindExternalAccount(final BindExternalAccountParam request);

    User getUserById(final Long id);

    Optional<User> findUserBoundToExternalAccount(final String externalAccountUid, final String providerName);
}
