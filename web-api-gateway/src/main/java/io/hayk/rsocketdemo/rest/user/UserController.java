package io.hayk.rsocketdemo.rest.user;

import io.hayk.rsocketdemo.api.model.BindExternalAccountResult;
import io.hayk.rsocketdemo.client.user.UserApiClient;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccountDetailsResolverProvider;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccountDetails;
import io.hayk.rsocketdemo.rest.common.BaseController;
import io.hayk.rsocketdemo.api.model.BindExternalAccountRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserApiClient userApiClient;

    public UserController(final UserApiClient userApiClient,
                          final ExternalAccountDetailsResolverProvider externalAccountDetailsResolverProvider,
                          @Qualifier("webFluxValidator") final Validator validator) {
        super(externalAccountDetailsResolverProvider, validator);
        this.userApiClient = userApiClient;
    }

    @PostMapping("/bind-external-account")
    public Mono<BindExternalAccountResult> bindGoogleAccount(final Mono<Principal> principal) {
        return principal.filter(OAuth2AuthenticationToken.class::isInstance)
                .map(OAuth2AuthenticationToken.class::cast)
                .map(this::extractExternalAccountDetails)
                .flatMap(this::bindExternalAccount);
    }

    private Mono<BindExternalAccountResult> bindExternalAccount(final ExternalAccountDetails externalAccountDetails) {
        return userApiClient.bindExternalAccount(new BindExternalAccountRequest(externalAccountDetails.email(),
                externalAccountDetails.uid(), externalAccountDetails.provider()));
    }

}
