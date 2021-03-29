package io.hayk.rsocketdemo.client.user;

import io.hayk.rsocketdemo.api.model.BindExternalAccountRequest;
import io.hayk.rsocketdemo.api.model.BindExternalAccountResult;
import io.hayk.rsocketdemo.api.model.GetUserIdByExternalAccountRequest;
import io.hayk.rsocketdemo.api.model.GetUserIdByExternalAccountUidResult;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;

class DefaultUserApiClient implements UserApiClient{

    private final Mono<RSocketRequester> userApiRequester;

    DefaultUserApiClient(final Mono<RSocketRequester> userApiRequester) {
        this.userApiRequester = userApiRequester;
    }

    @Override
    public Mono<BindExternalAccountResult> bindExternalAccount(final BindExternalAccountRequest bindExternalAccountRequest) {
        return userApiRequester.flatMap(rSocketRequester ->
                rSocketRequester.route("user:bind-external-account")
                        .data(bindExternalAccountRequest)
                        .retrieveMono(BindExternalAccountResult.class));
    }

    @Override
    public Mono<GetUserIdByExternalAccountUidResult> getUserIdByExternalAccount(final GetUserIdByExternalAccountRequest request) {
        return userApiRequester.flatMap(
                rSocketRequester ->
                        rSocketRequester.route("user:search-by-external-account")
                                .data(request)
                                .retrieveMono(GetUserIdByExternalAccountUidResult.class)

        );
    }
}
