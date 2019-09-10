package io.hayk.rsocketdemo;

import io.hayk.rsocketdemo.security.auth.external.ExternalAccount;

import java.util.Optional;

public interface UserService {

    ExternalAccount bindExternalAccount(final BindExternalAccountParam request);

    User getUserById(final Long id);

    Optional<User> findUserBoundToExternalAccount(final String externalAccountUid, final String providerName);
}
