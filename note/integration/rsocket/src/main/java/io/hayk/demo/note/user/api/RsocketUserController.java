package io.hayk.demo.note.user.api;

import io.hayk.demo.common.SimpleApplicationError;
import io.hayk.demo.note.user.BindExternalAccountParam;
import io.hayk.demo.note.user.BindExternalAccountRequest;
import io.hayk.demo.note.user.BindExternalAccountResponse;
import io.hayk.demo.note.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Controller
public class RsocketUserController {

    private final UserService userService;

    private final Executor executor;

    public RsocketUserController(final UserService userService,
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
}
