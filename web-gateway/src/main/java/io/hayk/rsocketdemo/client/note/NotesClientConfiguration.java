package io.hayk.rsocketdemo.client.note;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Mono;

@Configuration
class NoteClientConfiguration {

    private static final MimeType RSOCKET_MESSAGE_V0 = new MimeType("message", "x.rsocket.routing.v0");

    @Bean
    NoteApiClient noteApiClient(final Mono<RSocketRequester> notesApiRequester){
        return new DefaultNoteApiClient(notesApiRequester);
    }

    @Bean
    Mono<RSocketRequester> notesApiRequester(final RSocketStrategies rSocketStrategies,
                                             @Value("${rsocket.client.port:7000}") final int port) {
        return rSocket(port)
                .map(rSocket -> RSocketRequester
                        .wrap(rSocket,
                                MimeTypeUtils.APPLICATION_JSON,
                                RSOCKET_MESSAGE_V0,
                                rSocketStrategies));
    }

    private Mono<RSocket> rSocket(final int port) {
        return RSocketFactory
                .connect()
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .metadataMimeType(RSOCKET_MESSAGE_V0.toString())
                .transport(TcpClientTransport.create(port))
                .start();
    }
}
