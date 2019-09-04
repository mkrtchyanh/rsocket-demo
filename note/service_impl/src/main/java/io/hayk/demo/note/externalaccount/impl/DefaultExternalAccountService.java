package io.hayk.demo.note.externalaccount.impl;

import io.hayk.demo.note.externalaccount.ExternalAccountProvider;
import io.hayk.demo.note.externalaccount.ExternalAccountService;
import io.hayk.demo.note.externalaccount.repository.ExternalAccountProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
class DefaultExternalAccountService implements ExternalAccountService {

    private final ExternalAccountProviderRepository externalAccountProviderRepository;

    DefaultExternalAccountService(final ExternalAccountProviderRepository externalAccountProviderRepository) {
        this.externalAccountProviderRepository = externalAccountProviderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExternalAccountProvider> lookupExternalAccountProvider(final String name) {
        assertValidProviderName(name);
        return externalAccountProviderRepository.findByName(name);
    }

    @Override
    @Transactional
    public ExternalAccountProvider registerExternalAccountProvider(final String name) {
        assertValidProviderName(name);
        return externalAccountProviderRepository.save(new ExternalAccountProvider(name));
    }

    private void assertValidProviderName(final String name) {
        Assert.hasText(name, "Null or empty text was provided as an argument for parameter 'name'.");
    }
}
