package io.hayk.rsocketdemo.security.auth.external.google;

import io.hayk.rsocketdemo.security.auth.external.ExternalAccountDetails;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccountDetailsResolver;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

@Component
class GoogleExternalAccountDataExtractor implements ExternalAccountDetailsResolver {

    @Override
    public ExternalAccountDetails resolve(final OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return ExternalAccountDetails.of(
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
