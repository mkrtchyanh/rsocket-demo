package io.hayk.rsocketdemo.note.user;

import io.hayk.rsocketdemo.note.externalaccount.ExternalAccount;

import java.util.Optional;

public interface UserService {

    ExternalAccount bindExternalAccount(final BindExternalAccountParam request);

    User getUserById(final Long id);

    Optional<User> findUserBoundToExternalAccount(final String externalAccountUid, final String providerName);
}
