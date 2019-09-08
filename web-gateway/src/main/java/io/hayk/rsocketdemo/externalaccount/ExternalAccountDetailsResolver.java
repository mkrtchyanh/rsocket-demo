package io.hayk.rsocketdemo.externalaccount;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface ExternalAccountDetailsResolver {

    ExtractExternalAccountDetails resolve(final OAuth2AuthenticationToken oAuth2AuthenticationToken);
}
