package io.hayk.rsocketdemo.externalaccount;

import java.util.Optional;

public interface ExternalAccountDetailsResolverProvider {

    Optional<ExternalAccountDetailsResolver> forAuthorizedClientRegistrationId(final String authorizedClientRegistrationId);
}
