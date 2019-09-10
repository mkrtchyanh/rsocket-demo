package io.hayk.rsocketdemo.security.auth.external;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class DefaultExternalAccountDetailsResolverProvider implements ExternalAccountDetailsResolverProvider {

    private final Map<String, ExternalAccountDetailsResolver> extractExternalAccountDataExtractors;

    DefaultExternalAccountDetailsResolverProvider(
            final List<ExtractExternalAccountDataExtractorRegistry> extractExternalAccountDataExtractorRegistries) {
        this.extractExternalAccountDataExtractors = extractExternalAccountDataExtractorRegistries.stream()
                .collect(Collectors.toMap(ExtractExternalAccountDataExtractorRegistry::authorizedClientRegistrationId, ExtractExternalAccountDataExtractorRegistry::extractExternalAccountDataExtractor));
    }

    @Override
    public Optional<ExternalAccountDetailsResolver> forAuthorizedClientRegistrationId(final String authorizedClientRegistrationId) {
        return Optional.ofNullable(extractExternalAccountDataExtractors.get(authorizedClientRegistrationId));
    }
}
