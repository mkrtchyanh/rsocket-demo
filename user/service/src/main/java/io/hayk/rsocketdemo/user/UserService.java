package io.hayk.rsocketdemo.user;


import java.util.Optional;

public interface UserService {

    BindExternalAccountResult bindExternalAccount(final BindExternalAccountParam bindExternalAccountParam);

    Optional<Long> findUserIdBoundToExternalAccount(final String externalAccountUid, final String providerName);
}
