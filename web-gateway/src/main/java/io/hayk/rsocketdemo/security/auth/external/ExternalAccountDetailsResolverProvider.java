package io.hayk.rsocketdemo.security.auth.external;

import java.util.Optional;

public interface ExternalAccountDetailsResolverProvider {

    Optional<ExternalAccountDetailsResolver> forAuthorizedClientRegistrationId(final String authorizedClientRegistrationId);
}
