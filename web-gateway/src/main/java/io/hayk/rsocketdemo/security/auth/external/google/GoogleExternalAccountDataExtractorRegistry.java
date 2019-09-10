package io.hayk.rsocketdemo.security.auth.external.google;

import io.hayk.rsocketdemo.security.auth.external.ExternalAccountDetailsResolver;
import io.hayk.rsocketdemo.security.auth.external.ExtractExternalAccountDataExtractorRegistry;
import org.springframework.stereotype.Component;

@Component
class GoogleExternalAccountDataExtractorRegistry implements ExtractExternalAccountDataExtractorRegistry {

    private final ExternalAccountDetailsResolver googleExternalAccountDataExtractor;

    GoogleExternalAccountDataExtractorRegistry(final ExternalAccountDetailsResolver googleExternalAccountDataExtractor) {
        this.googleExternalAccountDataExtractor = googleExternalAccountDataExtractor;
    }

    @Override
    public String authorizedClientRegistrationId() {
        return "google";
    }

    @Override
    public ExternalAccountDetailsResolver extractExternalAccountDataExtractor() {
        return googleExternalAccountDataExtractor;
    }
}
