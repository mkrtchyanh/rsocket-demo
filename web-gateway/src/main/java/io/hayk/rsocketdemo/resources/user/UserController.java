package io.hayk.rsocketdemo.resources.user;

import io.hayk.rsocketdemo.client.user.UserApiClient;
import io.hayk.rsocketdemo.externalaccount.ExternalAccountDetailsResolverProvider;
import io.hayk.rsocketdemo.externalaccount.ExtractExternalAccountDetails;
import io.hayk.rsocketdemo.resources.common.BaseController;
import io.hayk.rsocketdemo.BindExternalAccountRequest;
import io.hayk.rsocketdemo.BindExternalAccountResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class UserController extends BaseController {

    private final UserApiClient userApiClient;

    public UserController(final UserApiClient userApiClient,
                          final ExternalAccountDetailsResolverProvider externalAccountDetailsResolverProvider,
                          @Qualifier("webFluxValidator") final Validator validator) {
        super(externalAccountDetailsResolverProvider, validator);
        this.userApiClient = userApiClient;
    }

    @PostMapping("/api/user/bind-external-account")
    public Mono<BindExternalAccountResult> bindGoogleAccount(final Mono<Principal> principal) {
        return principal.filter(OAuth2AuthenticationToken.class::isInstance)
                .map(OAuth2AuthenticationToken.class::cast)
                .map(this::extractExternalAccountDetails)
                .flatMap(this::bindExternalAccount);
    }

    private Mono<BindExternalAccountResult> bindExternalAccount(final ExtractExternalAccountDetails extractExternalAccountDetails) {
        return userApiClient.bindExternalAccount(new BindExternalAccountRequest(extractExternalAccountDetails.email(),
                extractExternalAccountDetails.uid(), extractExternalAccountDetails.provider()));
    }

}
