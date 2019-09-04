package io.hayk.demo.externalaccount;

import java.util.Optional;

public interface ExternalAccountService {

    Optional<ExternalAccountProvider> lookupExternalAccountProvider(final String name);

    ExternalAccountProvider registerExternalAccountProvider(final String providerName);

}
