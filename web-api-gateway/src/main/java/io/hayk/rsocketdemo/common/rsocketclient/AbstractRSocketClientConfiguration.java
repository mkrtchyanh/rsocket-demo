package io.hayk.rsocketdemo.common.rsocketclient;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Mono;

public abstract class AbstractRSocketClientConfiguration {

    private static final MimeType RSOCKET_MESSAGE_V0 = new MimeType("message", "x.rsocket.routing.v0");


    protected Mono<RSocketRequester> apiRequester(final RSocketStrategies rSocketStrategies,
                                                  final String host,
                                                  final int port) {
        return rSocket(host, port)
                .map(rSocket -> RSocketRequester
                        .wrap(rSocket,
                                MimeTypeUtils.APPLICATION_JSON,
                                RSOCKET_MESSAGE_V0,
                                rSocketStrategies));
    }

    private Mono<RSocket> rSocket(final String host, final int port) {
        return RSocketFactory
                .connect()
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .metadataMimeType(RSOCKET_MESSAGE_V0.toString())
                .transport(TcpClientTransport.create(host, port))
                .start();
    }
}
