package io.hayk.demo.resources.note.api;


import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final Mono<RSocketRequester> notesApiRequester;

    public NoteController(final Mono<RSocketRequester> notesApiRequester) {
        this.notesApiRequester = notesApiRequester;
    }
}
