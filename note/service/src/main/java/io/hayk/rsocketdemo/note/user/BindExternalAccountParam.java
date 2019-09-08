package io.hayk.rsocketdemo.note.user;

public interface BindExternalAccountParam {

    static BindExternalAccountParam of(final String email, final String externalAccountUid, final String providerName) {
        return new ImmutableBindExternalAccountParams(email, externalAccountUid, providerName);
    }

    String email();

    String externalAccountUid();

    String providerName();
}
