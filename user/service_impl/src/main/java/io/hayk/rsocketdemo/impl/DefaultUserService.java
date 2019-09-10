package io.hayk.rsocketdemo.impl;

import io.hayk.rsocketdemo.User;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccountService;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccount;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccountProvider;
import io.hayk.rsocketdemo.BindExternalAccountParam;
import io.hayk.rsocketdemo.UserService;
import io.hayk.rsocketdemo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final ExternalAccountService externalAccountService;

    DefaultUserService(final UserRepository userRepository, final ExternalAccountService externalAccountService) {
        this.userRepository = userRepository;
        this.externalAccountService = externalAccountService;
    }

    private static void assertValidBindExternalAccountParams(final BindExternalAccountParam params) {
        Assert.notNull(params, "Null was passed as ana rgument for parameter 'params'.");
        Assert.notNull(params.email(), "Null or empty text was passed as ana rgument for parameter 'params.email'.");
        Assert.notNull(params.externalAccountUid(), "Nul or empty textl was passed as ana rgument for parameter 'params.externalAccountUid'.");
        Assert.notNull(params.providerName(), "Null or empty text was passed as ana rgument for parameter 'params.providerName'.");
    }

    @Transactional
    public ExternalAccount bindExternalAccount(final BindExternalAccountParam params) {
        assertValidBindExternalAccountParams(params);
        final ExternalAccountProvider provider = externalAccountService.lookupExternalAccountProvider(params.providerName())
                .orElseGet(() -> externalAccountService.registerExternalAccountProvider(params.providerName()));
        final User user = userRepository.findByEmail(params.email())
                .orElseGet(() -> userRepository.save(new User(params.email())));
        return user.bindExternalAccount(params.externalAccountUid(), provider);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(final Long id) {
        Assert.notNull(id, "Null was passed as ana argument for parameter 'id'.");
        return userRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserBoundToExternalAccount(final String externalAccountUid, final String providerName) {
        Assert.hasText(externalAccountUid, "Null or empty text was passed as ana argument for parameter 'externalAccountUid'.");
        Assert.hasText(providerName, "Null or empty text was passed as ana argument for parameter 'providerName'.");
        return externalAccountService.getExternalAccountByUidAndProviderName(externalAccountUid, providerName)
                .map(ExternalAccount::getOwner);
    }
}
