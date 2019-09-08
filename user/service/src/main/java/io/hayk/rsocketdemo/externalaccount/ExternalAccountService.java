package io.hayk.rsocketdemo.externalaccount;

import java.util.Optional;

public interface ExternalAccountService {

    Optional<ExternalAccountProvider> lookupExternalAccountProvider(final String name);

    ExternalAccountProvider registerExternalAccountProvider(final String providerName);

    Optional<ExternalAccount> getExternalAccountByUidAndProviderName(final String externalAccountUid, final String providerName);
}
