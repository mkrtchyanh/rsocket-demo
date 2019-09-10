package io.hayk.rsocketdemo.security.auth.external.impl;

import io.hayk.rsocketdemo.AbstractServiceUnitTest;
import io.hayk.rsocketdemo.security.auth.external.ExternalAccountProvider;
import io.hayk.rsocketdemo.impl.ExternalAccountService;
import io.hayk.rsocketdemo.security.auth.external.repository.ExternalAccountProviderRepository;
import io.hayk.rsocketdemo.security.auth.external.repository.ExternalAccountRepository;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;

public class DefaultExternalAccountServiceTest extends AbstractServiceUnitTest {

    private ExternalAccountService externalAccountService;

    @Mock
    private ExternalAccountProviderRepository externalAccountProviderRepository;

    @Mock
    private ExternalAccountRepository accountRepository;

    @Before
    public void prepare() {
        externalAccountService = new DefaultExternalAccountService(externalAccountProviderRepository,
                accountRepository);
    }

    @Test
    public void testLookupExternalAccountProviderWithIllegalArgs() {
        replayAll();
        assertThatThrownBy(() -> externalAccountService.lookupExternalAccountProvider(null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> externalAccountService.lookupExternalAccountProvider(""))
                .isInstanceOf(IllegalArgumentException.class);
        verifyAll();
    }

    @Test
    public void testLookupExternalAccountProviderWhenProviderIsMissing() {
        final String providerName = uuid();
        expect(externalAccountProviderRepository.findByName(providerName)).andReturn(Optional.empty());
        replayAll();
        assertThat(externalAccountService.lookupExternalAccountProvider(providerName))
                .isEmpty();
        verifyAll();
    }

    @Test
    public void testLookupExternalAccountProviderWhenProviderIsPresent() {
        final ExternalAccountProvider provider = new ExternalAccountProvider(uuid());
        expect(externalAccountProviderRepository.findByName(provider.getName())).andReturn(Optional.of(provider));
        replayAll();
        assertThat(externalAccountService.lookupExternalAccountProvider(provider.getName())
                .orElseThrow(() -> new AssertionError("Provider should be present!")))
                .isEqualTo(provider);
        verifyAll();
    }

    @Test
    public void testRegisterExternalAccountProviderWithIllegalArgs() {
        replayAll();
        assertThatThrownBy(() -> externalAccountService.registerExternalAccountProvider(null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> externalAccountService.registerExternalAccountProvider(""))
                .isInstanceOf(IllegalArgumentException.class);
        verifyAll();
    }

    @Test
    public void testRegisterExternalAccountProvider() {
        final String providerName = uuid();
        expect(externalAccountProviderRepository.save(isA(ExternalAccountProvider.class)))
                .andAnswer(() -> new ExternalAccountProvider(providerName));
        replayAll();
        assertThat(externalAccountService.registerExternalAccountProvider(providerName))
                .hasFieldOrPropertyWithValue("name", providerName);
        verifyAll();
    }
}
