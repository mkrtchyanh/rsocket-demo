package io.hayk.rsocketdemo.externalaccount.google;

import io.hayk.rsocketdemo.externalaccount.ExternalAccountDetailsResolver;
import io.hayk.rsocketdemo.externalaccount.ExtractExternalAccountDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

@Component
class GoogleExternalAccountDataExtractor implements ExternalAccountDetailsResolver {

    @Override
    public ExtractExternalAccountDetails resolve(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return ExtractExternalAccountDetails.of(
                extractExternalAccountUId(oAuth2AuthenticationToken),
                email(oAuth2AuthenticationToken),
                oAuth2AuthenticationToken.getAuthorizedClientRegistrationId()
        );
    }

    private static String extractExternalAccountUId(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return (String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("sub");
    }

    private static String email(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return (String) oAuth2AuthenticationToken.getPrincipal().getAttributes().get("email");
    }
}
