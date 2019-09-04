package io.hayk.demo.user;

import io.hayk.demo.AbstractServiceUnitTest;
import io.hayk.demo.externalaccount.ExternalAccountProvider;
import io.hayk.demo.externalaccount.ExternalAccountService;
import io.hayk.demo.user.repository.UserRepository;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.easymock.EasyMock.*;

public class DefaultUserServiceTest extends AbstractServiceUnitTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExternalAccountService externalAccountService;

    @Before
    public void prepare() {
        userService = new DefaultUserService(userRepository, externalAccountService);
    }

    @Test
    public void testBindExternalAccountWithIllegalArgs() {
        replayAll();
        assertThatThrownBy(() -> userService.bindExternalAccount(null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> userService.bindExternalAccount(withoutEmailBindExternalAccountParams()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> userService.bindExternalAccount(withoutExternalAccountUidBindExternalAccountParams()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> userService.bindExternalAccount(withoutProviderNameBindExternalAccountParams()))
                .isInstanceOf(IllegalArgumentException.class);
        verifyAll();
    }

    @Test
    public void testBindExternalAccountWhenProviderAndUserAreMissing() {
        final BindExternalAccountParams params = validBindExternalAccountParams();
        expect(externalAccountService.lookupExternalAccountProvider(params.providerName()))
                .andReturn(Optional.empty());
        expect(externalAccountService.registerExternalAccountProvider(params.providerName()))
                .andAnswer(() -> new ExternalAccountProvider(params.providerName()));
        expect(userRepository.findByEmail(params.email())).andReturn(Optional.empty());
        expect(userRepository.save(isA(User.class))).andAnswer(() -> (User) getCurrentArguments()[0]);
        replayAll();
        assertThat(userService.bindExternalAccount(params))
                .hasFieldOrPropertyWithValue("uid", params.externalAccountUid())
                .hasFieldOrPropertyWithValue("provider.name", params.providerName())
                .hasFieldOrPropertyWithValue("owner.email", params.email());
        verifyAll();
    }

    @Test
    public void testBindExternalAccountWhenProviderAndUserArePresent() {
        final BindExternalAccountParams params = validBindExternalAccountParams();
        expect(externalAccountService.lookupExternalAccountProvider(params.providerName()))
                .andAnswer(() -> Optional.of(new ExternalAccountProvider(params.providerName())));
        expect(userRepository.findByEmail(params.email())).andAnswer(() -> Optional.of(new User(params.email())));
        replayAll();
        assertThat(userService.bindExternalAccount(params))
                .hasFieldOrPropertyWithValue("uid", params.externalAccountUid())
                .hasFieldOrPropertyWithValue("provider.name", params.providerName())
                .hasFieldOrPropertyWithValue("owner.email", params.email());
        verifyAll();
    }

    @Test
    public void testBindExternalAccountProviderIsPresentAndUserAreIsMissing() {
        final BindExternalAccountParams params = validBindExternalAccountParams();
        expect(externalAccountService.lookupExternalAccountProvider(params.providerName()))
                .andAnswer(() -> Optional.of(new ExternalAccountProvider(params.providerName())));
        expect(userRepository.findByEmail(params.email())).andReturn(Optional.empty());
        expect(userRepository.save(isA(User.class))).andAnswer(() -> (User) getCurrentArguments()[0]);
        replayAll();
        assertThat(userService.bindExternalAccount(params))
                .hasFieldOrPropertyWithValue("uid", params.externalAccountUid())
                .hasFieldOrPropertyWithValue("provider.name", params.providerName())
                .hasFieldOrPropertyWithValue("owner.email", params.email());
        verifyAll();
    }

    @Test
    public void testBindExternalAccountWhenAlreadyBound() {
        final BindExternalAccountParams params = validBindExternalAccountParams();
        final User user = new User(params.email());
        final ExternalAccountProvider provider = new ExternalAccountProvider(params.providerName());
        user.bindExternalAccount(params.externalAccountUid(), provider);
        expect(externalAccountService.lookupExternalAccountProvider(params.providerName()))
                .andReturn(Optional.of(provider));
        expect(userRepository.findByEmail(params.email()))
                .andReturn(Optional.of(user));
        replayAll();
        assertThat(userService.bindExternalAccount(params))
                .hasFieldOrPropertyWithValue("uid", params.externalAccountUid())
                .hasFieldOrPropertyWithValue("provider.name", params.providerName())
                .hasFieldOrPropertyWithValue("owner.email", params.email());
        verifyAll();
    }

    private static BindExternalAccountParams validBindExternalAccountParams() {
        return BindExternalAccountParams.of(uuid(), uuid(), uuid());
    }

    private static BindExternalAccountParams withoutEmailBindExternalAccountParams() {
        return new SimpleBindExternalAccountParams(null, uuid(), uuid());
    }

    private static BindExternalAccountParams withoutExternalAccountUidBindExternalAccountParams() {
        return new SimpleBindExternalAccountParams(uuid(), null, uuid());
    }

    private static BindExternalAccountParams withoutProviderNameBindExternalAccountParams() {
        return new SimpleBindExternalAccountParams(uuid(), uuid(), null);
    }


    private static final class SimpleBindExternalAccountParams implements BindExternalAccountParams {

        private final String email;

        private final String externalAccountUid;

        private final String providerName;

        SimpleBindExternalAccountParams(final String email, final String externalAccountUid, final String providerName) {
            this.email = email;
            this.externalAccountUid = externalAccountUid;
            this.providerName = providerName;
        }

        @Override
        public String email() {
            return email;
        }

        @Override
        public String externalAccountUid() {
            return externalAccountUid;
        }

        @Override
        public String providerName() {
            return providerName;
        }
    }
}
