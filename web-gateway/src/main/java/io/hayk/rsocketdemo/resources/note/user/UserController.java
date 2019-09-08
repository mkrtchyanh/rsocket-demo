package io.hayk.rsocketdemo.resources.note.user;

import io.hayk.rsocketdemo.client.note.NoteApiClient;
import io.hayk.rsocketdemo.externalaccount.ExternalAccountDetailsResolverProvider;
import io.hayk.rsocketdemo.externalaccount.ExtractExternalAccountDetails;
import io.hayk.rsocketdemo.note.user.BindExternalAccountRequest;
import io.hayk.rsocketdemo.note.user.BindExternalAccountResult;
import io.hayk.rsocketdemo.resources.common.BaseController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class UserController extends BaseController {

    private final NoteApiClient noteApiClient;

    public UserController(final NoteApiClient noteApiClient,
                          final ExternalAccountDetailsResolverProvider externalAccountDetailsResolverProvider,
                          @Qualifier("webFluxValidator") final Validator validator) {
        super(externalAccountDetailsResolverProvider, validator);
        this.noteApiClient = noteApiClient;
    }

    @PostMapping("/api/user/bind-external-account")
    public Mono<BindExternalAccountResult> bindGoogleAccount(final Mono<Principal> principal) {
        return principal.filter(OAuth2AuthenticationToken.class::isInstance)
                .map(OAuth2AuthenticationToken.class::cast)
                .map(this::extractExternalAccountDetails)
                .flatMap(this::bindExternalAccount);
    }

    private Mono<BindExternalAccountResult> bindExternalAccount(final ExtractExternalAccountDetails extractExternalAccountDetails) {
        return noteApiClient.bindExternalAccount(new BindExternalAccountRequest(extractExternalAccountDetails.email(),
                extractExternalAccountDetails.uid(), extractExternalAccountDetails.provider()));
    }

}
