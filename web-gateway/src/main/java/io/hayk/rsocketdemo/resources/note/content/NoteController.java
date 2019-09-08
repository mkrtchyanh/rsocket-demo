package io.hayk.rsocketdemo.resources.note.content;


import io.hayk.rsocketdemo.client.note.NoteApiClient;
import io.hayk.rsocketdemo.note.common.AbstractResult;
import io.hayk.rsocketdemo.externalaccount.ExternalAccountDetailsResolverProvider;
import io.hayk.rsocketdemo.externalaccount.ExtractExternalAccountDetails;
import io.hayk.rsocketdemo.note.content.*;
import io.hayk.rsocketdemo.note.user.BindExternalAccountRequest;
import io.hayk.rsocketdemo.note.user.BindExternalAccountResult;
import io.hayk.rsocketdemo.note.user.GetUserIdByExternalAccountRequest;
import io.hayk.rsocketdemo.note.user.GetUserIdByExternalAccountUidResult;
import io.hayk.rsocketdemo.resources.common.BaseController;
import io.hayk.rsocketdemo.resources.common.ValidationFailedException;
import io.hayk.rsocketdemo.resources.note.content.model.CreateNoteWebRequest;
import io.hayk.rsocketdemo.resources.note.content.model.UpdateNoteWebRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/note")
public class NoteController extends BaseController {

    private final NoteApiClient noteApiClient;

    public NoteController(final NoteApiClient noteApiClient,
                          final ExternalAccountDetailsResolverProvider externalAccountDetailsResolverProvider,
                          @Qualifier("webFluxValidator") final Validator validator) {
        super(externalAccountDetailsResolverProvider, validator);
        this.noteApiClient = noteApiClient;
    }

    @PostMapping(value = "/")
    public Mono<GenericNoteResult> create(@RequestBody final Mono<CreateNoteWebRequest> createNoteRequest, final Mono<Principal> principal) {
        return prepareUserId(principal)
                .flatMap(userId ->
                        createNoteRequest
                                .doOnSuccess(this::validate)
                                .flatMap(request -> noteApiClient.create(createNoteRequest(request, userId)))
                )
                .onErrorResume(ValidationFailedException.class,
                        this::resumeValidationFailed
                );
    }

    private static CreateNoteRequest createNoteRequest(final CreateNoteWebRequest webRequest, final Long userId) {
        final CreateNoteRequest request = new CreateNoteRequest();
        request.setText(webRequest.getText());
        request.setTitle(webRequest.getTitle());
        request.setUserId(userId);
        return request;
    }

    @PutMapping("/")
    public Mono<GenericNoteResult> update(@RequestBody final Mono<UpdateNoteWebRequest> updateNoteRequest, final Mono<Principal> principal) {
        return userId(principal)
                .flatMap(userId ->
                        updateNoteRequest
                                .doOnSuccess(this::validate)
                                .flatMap(request -> noteApiClient.update(updateNoteRequest(request, userId)))
                )
                .onErrorResume(ValidationFailedException.class,
                        this::resumeValidationFailed
                );
    }

    private Mono<GenericNoteResult> resumeValidationFailed(ValidationFailedException ex) {
        return Mono.just(
                AbstractResult.withErros(
                        GenericNoteResult::new,
                        ex.getErrors()
                ));
    }

    private static UpdateNoteRequest updateNoteRequest(final UpdateNoteWebRequest webRequest, final Long userId) {
        final UpdateNoteRequest request = new UpdateNoteRequest();
        request.setText(webRequest.getText());
        request.setTitle(webRequest.getTitle());
        request.setUserId(userId);
        request.setNoteId(webRequest.getNoteId());
        return request;
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<GenericNoteResult>> getNote(@PathVariable("id") final Long id) {
        return noteApiClient.getNote(new GetNoteRequest(id))
                .map(ResponseEntity.ok()::body)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/")
    public Mono<GetUserNotesResult> getUserNotes(final Mono<Principal> principal) {
        return userId(principal)
                .flatMap(userId ->
                        noteApiClient.getUserNotes(new GetUserNotesRequest(userId))
                );
    }

    private Mono<ExtractExternalAccountDetails> externalAccountDetails(final Mono<Principal> principal) {
        return principal
                .map(OAuth2AuthenticationToken.class::cast)
                .map(this::extractExternalAccountDetails);
    }


    private Mono<Long> prepareUserId(final Mono<Principal> principal) {
        return externalAccountDetails(principal)
                .flatMap(this::userIdForExternalAccount);

    }

    private Mono<Long> userIdForExternalAccount(ExtractExternalAccountDetails externalAccountDetails) {
        return getUserIdByExternalAccount(externalAccountDetails)
                .switchIfEmpty(
                        noteApiClient.bindExternalAccount(
                                new BindExternalAccountRequest(
                                        externalAccountDetails.email(),
                                        externalAccountDetails.uid(),
                                        externalAccountDetails.provider()
                                )
                        )
                                .map(BindExternalAccountResult::getUserId)
                );
    }


    private Mono<Long> userId(final Mono<Principal> principal) {
        return externalAccountDetails(principal)
                .flatMap(this::getUserIdByExternalAccount);

    }

    private Mono<Long> getUserIdByExternalAccount(ExtractExternalAccountDetails extractExternalAccountDetails) {
        return noteApiClient.getUserIdByExternalAccount(
                new GetUserIdByExternalAccountRequest(
                        extractExternalAccountDetails.uid(),
                        extractExternalAccountDetails.provider()
                )
        ).map(GetUserIdByExternalAccountUidResult::getUserId);
    }
}
