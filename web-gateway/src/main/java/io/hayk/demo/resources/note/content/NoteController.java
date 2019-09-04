package io.hayk.demo.resources.note.content;


import io.hayk.demo.client.note.NoteApiClient;
import io.hayk.demo.note.content.*;
import io.hayk.demo.note.user.GetUserIdByExternalAccountUidRequest;
import io.hayk.demo.note.user.GetUserIdByExternalAccountUidResult;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final NoteApiClient noteApiClient;

    public NoteController(final NoteApiClient noteApiClient) {
        this.noteApiClient = noteApiClient;
    }

    private static CreateNoteRequest createNoteRequest(final CreateNoteWebRequest webRequest, final Long userId) {
        final CreateNoteRequest request = new CreateNoteRequest();
        request.setText(webRequest.getText());
        request.setTitle(webRequest.getTitle());
        request.setUserId(userId);
        return request;
    }

    private static UpdateNoteRequest updateNoteRequest(final UpdateNoteWebRequest webRequest, final Long userId) {
        final UpdateNoteRequest request = new UpdateNoteRequest();
        request.setText(webRequest.getText());
        request.setTitle(webRequest.getTitle());
        request.setUserId(userId);
        request.setNoteId(webRequest.getNoteId());
        return request;
    }

    @PostMapping("/create")
    public Mono<GenericNoteResult> create(final Mono<CreateNoteWebRequest> createNoteRequest, final Mono<Principal> principal) {
        return userId(principal).flatMap(userId ->
                createNoteRequest
                        .flatMap(request -> noteApiClient.create(createNoteRequest(request, userId))));
    }

    @PutMapping("/update")
    public Mono<GenericNoteResult> update(final Mono<UpdateNoteWebRequest> updateNoteRequest, final Mono<Principal> principal) {
        return userId(principal).flatMap(userId ->
                updateNoteRequest
                        .flatMap(request -> noteApiClient.update(updateNoteRequest(request, userId))));
    }

    @GetMapping("{id}")
    public Mono<GenericNoteResult> getNote(@PathVariable("id") final Long id) {
        return noteApiClient.getNote(new GetNoteRequest(id));
    }

    @GetMapping("/all")
    public Mono<GetUserNotesResult> getUserNotes(@RequestParam("user_id") final Mono<Principal> principal) {
        return userId(principal).flatMap(userId ->
                noteApiClient.getUserNotes(new GetUserNotesRequest(userId)));
    }

    private Mono<Long> userId(final Mono<Principal> principal) {
        return principal.map(OAuth2AuthenticationToken.class::cast)
                .map(OAuth2AuthenticationToken::getPrincipal)
                .map(OAuth2User::getAttributes)
                .map(attributes -> attributes.get("sub"))
                .map(String::valueOf)
                .flatMap(externalAccountUId ->
                        noteApiClient.getUserIdByExternalAccountUid(new GetUserIdByExternalAccountUidRequest(externalAccountUId))
                ).map(GetUserIdByExternalAccountUidResult::getUserId);
    }
}
