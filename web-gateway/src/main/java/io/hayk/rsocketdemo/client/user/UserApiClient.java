package io.hayk.rsocketdemo.client.user;

import io.hayk.rsocketdemo.BindExternalAccountRequest;
import io.hayk.rsocketdemo.BindExternalAccountResult;
import io.hayk.rsocketdemo.GetUserIdByExternalAccountRequest;
import io.hayk.rsocketdemo.GetUserIdByExternalAccountUidResult;
import reactor.core.publisher.Mono;

public interface UserApiClient {

    Mono<BindExternalAccountResult> bindExternalAccount(final BindExternalAccountRequest bindExternalAccountRequest);

    Mono<GetUserIdByExternalAccountUidResult> getUserIdByExternalAccount(final GetUserIdByExternalAccountRequest request);

}
