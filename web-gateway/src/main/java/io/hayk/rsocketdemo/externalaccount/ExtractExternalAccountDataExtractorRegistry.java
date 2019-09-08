package io.hayk.rsocketdemo.externalaccount;

public interface ExtractExternalAccountDataExtractorRegistry {

    String authorizedClientRegistrationId();

    ExternalAccountDetailsResolver extractExternalAccountDataExtractor();
}
