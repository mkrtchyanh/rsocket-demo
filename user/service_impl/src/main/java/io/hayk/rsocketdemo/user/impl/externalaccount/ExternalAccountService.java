package io.hayk.rsocketdemo.user.impl.externalaccount;

import io.hayk.rsocketdemo.user.externalaccount.ExternalAccount;
import io.hayk.rsocketdemo.user.externalaccount.ExternalAccountProvider;

import java.util.Optional;

public interface ExternalAccountService {

    Optional<ExternalAccountProvider> lookupExternalAccountProvider(final String name);

    ExternalAccountProvider registerExternalAccountProvider(final String providerName);

    Optional<ExternalAccount> getExternalAccountByUidAndProviderName(final String externalAccountUid, final String providerName);
}
