package io.hayk.demo.note.user;

import io.hayk.demo.note.externalaccount.ExternalAccount;

public interface UserService {

    ExternalAccount bindExternalAccount(final BindExternalAccountParams request);
}
