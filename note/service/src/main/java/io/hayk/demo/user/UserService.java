package io.hayk.demo.user;

import io.hayk.demo.externalaccount.ExternalAccount;

public interface UserService {

    ExternalAccount bindExternalAccount(final BindExternalAccountParams request);
}
