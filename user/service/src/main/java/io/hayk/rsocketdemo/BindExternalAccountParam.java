package io.hayk.rsocketdemo;

public interface BindExternalAccountParam {

    String email();

    String externalAccountUid();

    String providerName();

    static BindExternalAccountParam of(final String email, final String externalAccountUid, final String providerName) {
        return new ImmutableBindExternalAccountParams(email, externalAccountUid, providerName);
    }


}