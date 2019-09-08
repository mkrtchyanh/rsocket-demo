package io.hayk.rsocketdemo.note.user.api;

import io.hayk.rsocketdemo.common.SimpleApplicationError;
import io.hayk.rsocketdemo.note.user.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Controller
public class UserController {

    private final UserService userService;

    private final Executor executor;

    public UserController(final UserService userService,
                          @Qualifier("dbCallBoundExecutor") final Executor executor) {
        this.userService = userService;
        this.executor = executor;
    }

    @MessageMapping("user:bind-external-account")
    Mono<BindExternalAccountResponse> bindExternalAccount(final BindExternalAccountRequest request) {
        return Mono.fromCompletionStage(CompletableFuture.supplyAsync(() ->
                        userService.
                                bindExternalAccount(
                                        BindExternalAccountParam.of(request.getEmail(),
                                                request.getExternalAccountUid(),
                                                request.getProviderName())
                                )
                , executor)
                .handleAsync((externalAccount, th) -> {
                    if (th != null) {
                        return new BindExternalAccountResponse(Collections.singletonList(new SimpleApplicationError(th.getMessage())));
                    } else {
                        return new BindExternalAccountResponse(externalAccount.getOwner().getId());
                    }
                })
        );
    }

    @MessageMapping("user:search-by-external-account")
    Mono<GetUserIdByExternalAccountUidResult> getUserIdByExternalAccountUid(final GetUserIdByExternalAccountUidRequest request) {
        return Mono.fromCompletionStage(CompletableFuture.supplyAsync(() ->
                        userService.
                                findUserBoundToExternalAccount(
                                        request.getExternalAccountUid(),
                                        request.getProviderName()
                                )
                , executor)
                .thenApply(userHolder -> userHolder.map(User::getId))
        )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(GetUserIdByExternalAccountUidResult::new);
    }
}
