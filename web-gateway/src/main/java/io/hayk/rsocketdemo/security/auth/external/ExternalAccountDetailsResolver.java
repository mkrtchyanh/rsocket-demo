package io.hayk.rsocketdemo.security.auth.external;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface ExternalAccountDetailsResolver {

    ExternalAccountDetails resolve(final OAuth2AuthenticationToken oAuth2AuthenticationToken);
}
