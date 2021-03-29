package io.hayk.rsocketdemo.client.user;

import io.hayk.rsocketdemo.common.rsocketclient.AbstractRSocketClientConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Mono;

@Configuration
class UserApiClientConfiguration extends AbstractRSocketClientConfiguration {


    @Bean
    UserApiClient userApiClient(final Mono<RSocketRequester> userApiRequester) {
        return new DefaultUserApiClient(userApiRequester);
    }

    @Bean
    Mono<RSocketRequester> userApiRequester(final RSocketStrategies rSocketStrategies,
                                            @Value("${user.api.rsocket.host:127.0.0.1}") final String host,
                                            @Value("${user.api.rsocket.port:7002}") final int port) {
        return apiRequester(rSocketStrategies, host, port);
    }

}
