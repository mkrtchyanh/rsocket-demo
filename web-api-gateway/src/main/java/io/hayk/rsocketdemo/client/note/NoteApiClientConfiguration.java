package io.hayk.rsocketdemo.client.note;

import io.hayk.rsocketdemo.common.rsocketclient.AbstractRSocketClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Mono;

@Configuration
class NoteApiClientConfiguration extends AbstractRSocketClientConfiguration {


    @Bean
    NoteApiClient noteApiClient(final Mono<RSocketRequester> noteApiRequester) {
        return new DefaultNoteApiClient(noteApiRequester);
    }

    @Bean
    Mono<RSocketRequester> noteApiRequester(final RSocketStrategies rSocketStrategies,
                                            @Value("${note.api.rsocket.host:127.0.0.1}") final String host,
                                            @Value("${note.api.rsocket.port:7000}") final int port) {
        return apiRequester(rSocketStrategies, host, port);
    }

}
