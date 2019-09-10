package io.hayk.rsocketdemo.security.auth.external;

public interface ExtractExternalAccountDataExtractorRegistry {

    String authorizedClientRegistrationId();

    ExternalAccountDetailsResolver extractExternalAccountDataExtractor();
}
