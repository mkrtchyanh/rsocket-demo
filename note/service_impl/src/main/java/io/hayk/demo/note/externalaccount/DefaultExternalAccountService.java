package io.hayk.demo.note.externalaccount;

import io.hayk.demo.note.externalaccount.repository.ExternalAccountProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
class DefaultExternalAccountService implements ExternalAccountService {

    private final ExternalAccountProviderRepository externalAccountProviderRepository;

    DefaultExternalAccountService(final ExternalAccountProviderRepository externalAccountProviderRepository) {
        this.externalAccountProviderRepository = externalAccountProviderRepository;
    }

    @Override
    public Optional<ExternalAccountProvider> lookupExternalAccountProvider(final String name) {
        assertValidProviderName(name);
        return externalAccountProviderRepository.findByName(name);
    }

    @Override
    public ExternalAccountProvider registerExternalAccountProvider(final String name) {
        assertValidProviderName(name);
        return externalAccountProviderRepository.save(new ExternalAccountProvider(name));
    }

    private void assertValidProviderName(final String name) {
        Assert.hasText(name, "Null or empty text was provided as an argument for parameter 'name'.");
    }
}
