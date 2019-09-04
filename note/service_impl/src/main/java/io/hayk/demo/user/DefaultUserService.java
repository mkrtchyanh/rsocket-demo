package io.hayk.demo.user;

import io.hayk.demo.externalaccount.ExternalAccount;
import io.hayk.demo.externalaccount.ExternalAccountProvider;
import io.hayk.demo.externalaccount.ExternalAccountService;
import io.hayk.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final ExternalAccountService externalAccountService;

    DefaultUserService(final UserRepository userRepository, final ExternalAccountService externalAccountService) {
        this.userRepository = userRepository;
        this.externalAccountService = externalAccountService;
    }

    @Transactional
    public ExternalAccount bindExternalAccount(final BindExternalAccountParams params) {
        assertValidBindExternalAccountParams(params);
        final ExternalAccountProvider provider = externalAccountService.lookupExternalAccountProvider(params.providerName())
                .orElseGet(() -> externalAccountService.registerExternalAccountProvider(params.providerName()));
        final User user = userRepository.findByEmail(params.email())
                .orElseGet(() -> userRepository.save(new User(params.email())));
        return user.bindExternalAccount(params.externalAccountUid(), provider);
    }

    private static void assertValidBindExternalAccountParams(final BindExternalAccountParams params) {
        Assert.notNull(params, "Null was passed as ana rgument for parameter 'params'.");
        Assert.notNull(params.email(), "Null or empty text was passed as ana rgument for parameter 'params.email'.");
        Assert.notNull(params.externalAccountUid(), "Nul or empty textl was passed as ana rgument for parameter 'params.externalAccountUid'.");
        Assert.notNull(params.providerName(), "Null or empty text was passed as ana rgument for parameter 'params.providerName'.");
    }
}
