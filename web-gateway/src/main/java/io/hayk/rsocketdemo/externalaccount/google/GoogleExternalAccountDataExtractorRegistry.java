package io.hayk.rsocketdemo.externalaccount.google;

import io.hayk.rsocketdemo.externalaccount.ExternalAccountDetailsResolver;
import io.hayk.rsocketdemo.externalaccount.ExtractExternalAccountDataExtractorRegistry;
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
