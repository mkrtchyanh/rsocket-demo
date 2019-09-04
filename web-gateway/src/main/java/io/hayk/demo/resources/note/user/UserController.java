package io.hayk.demo.resources.note.user;

import io.hayk.demo.client.note.NoteApiClient;
import io.hayk.demo.note.user.BindExternalAccountRequest;
import io.hayk.demo.note.user.BindExternalAccountResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final NoteApiClient noteApiClient;

    public UserController(final NoteApiClient noteApiClient) {
        this.noteApiClient = noteApiClient;
    }

    @PostMapping("/connect/{provider}")
    public Mono<BindExternalAccountResponse> bindGoogleAccount(final Mono<Principal> principal,
                                                               @PathVariable("provider") final String provider) {
        return principal.filter(OAuth2AuthenticationToken.class::isInstance)
                .map(OAuth2AuthenticationToken.class::cast)
                .map(OAuth2AuthenticationToken::getPrincipal)
                .map(OAuth2User::getAttributes)
                .filter(Objects::nonNull)
                .flatMap(attributes -> bindExternalAccount(attributes, provider));
    }

    private Mono<BindExternalAccountResponse> bindExternalAccount(final Map<String, Object> attributes,
                                                                  final String provider) {
        return noteApiClient.bindExternalAccount(new BindExternalAccountRequest((String) attributes.get("email"), (String) attributes.get("sub"), provider));
    }
}
