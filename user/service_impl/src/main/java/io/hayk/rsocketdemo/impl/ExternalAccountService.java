package io.hayk.rsocketdemo.impl;

import io.hayk.rsocketdemo.security.auth.external.ExternalAccount;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccountProvider;

import java.util.Optional;

public interface ExternalAccountService {

    Optional<ExternalAccountProvider> lookupExternalAccountProvider(final String name);

    ExternalAccountProvider registerExternalAccountProvider(final String providerName);

    Optional<ExternalAccount> getExternalAccountByUidAndProviderName(final String externalAccountUid, final String providerName);
}
