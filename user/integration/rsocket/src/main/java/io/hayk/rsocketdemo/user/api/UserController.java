package io.hayk.rsocketdemo.user.api;


import io.hayk.rsocketdemo.*;
import io.hayk.rsocketdemo.api.model.BindExternalAccountRequest;
import io.hayk.rsocketdemo.api.model.GetUserIdByExternalAccountRequest;
import io.hayk.rsocketdemo.api.model.GetUserIdByExternalAccountUidResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final Executor executor;

    public UserController(final UserService userService,
                          @Qualifier("dbCallBoundExecutor") final Executor executor) {
        this.userService = userService;
        this.executor = executor;
    }

    @MessageMapping("user:bind-external-account")
    Mono<io.hayk.rsocketdemo.api.model.BindExternalAccountResult> bindExternalAccount(final BindExternalAccountRequest request) {
        return Mono.fromCompletionStage(CompletableFuture.supplyAsync(() ->
                        userService.
                                bindExternalAccount(
                                        BindExternalAccountParam.of(request.getEmail(),
                                                request.getExternalAccountUid(),
                                                request.getProviderName())
                                )
                , executor)
                .whenComplete((result, th) -> {
                    if (th != null) {
                        logger.error("Failed to create note!", th);
                    } else {
                        logger.debug("The external account with uid - {} was successfully bound to user with id {}.",
                                request.getExternalAccountUid(), result.userId());
                    }
                })
                .thenApply(result -> new io.hayk.rsocketdemo.api.model.BindExternalAccountResult(result.userId()))
        );
    }

    @MessageMapping("user:search-by-external-account")
    Mono<GetUserIdByExternalAccountUidResult> getUserIdByExternalAccountUid(final GetUserIdByExternalAccountRequest request) {
        return Mono.fromCompletionStage(CompletableFuture.supplyAsync(() ->
                        userService.
                                findUserIdBoundToExternalAccount(
                                        request.getExternalAccountUid(),
                                        request.getProviderName()
                                )
                , executor)
        )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(GetUserIdByExternalAccountUidResult::new);
    }
}
