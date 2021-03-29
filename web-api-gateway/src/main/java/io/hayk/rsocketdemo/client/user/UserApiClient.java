package io.hayk.rsocketdemo.client.user;

import io.hayk.rsocketdemo.api.model.BindExternalAccountRequest;
import io.hayk.rsocketdemo.api.model.BindExternalAccountResult;
import io.hayk.rsocketdemo.api.model.GetUserIdByExternalAccountRequest;
import io.hayk.rsocketdemo.api.model.GetUserIdByExternalAccountUidResult;
import reactor.core.publisher.Mono;

public interface UserApiClient {

    Mono<BindExternalAccountResult> bindExternalAccount(final BindExternalAccountRequest bindExternalAccountRequest);

    Mono<GetUserIdByExternalAccountUidResult> getUserIdByExternalAccount(final GetUserIdByExternalAccountRequest request);

}
