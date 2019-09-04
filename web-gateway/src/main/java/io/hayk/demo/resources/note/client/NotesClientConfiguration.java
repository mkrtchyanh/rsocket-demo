package io.hayk.demo.resources.note.client;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
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
    Mono<RSocketRequester> notesApiRequester(final RSocketStrategies rSocketStrategies) {
        return rSocket()
                .map(rSocket -> RSocketRequester
                        .wrap(rSocket,
                                MimeTypeUtils.APPLICATION_JSON,
                                RSOCKET_MESSAGE_V0,
                                rSocketStrategies));
    }

    private Mono<RSocket> rSocket() {
        return RSocketFactory
                .connect()
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .metadataMimeType(RSOCKET_MESSAGE_V0.toString())
                .transport(TcpClientTransport.create(7000))
                .start();
    }
}
