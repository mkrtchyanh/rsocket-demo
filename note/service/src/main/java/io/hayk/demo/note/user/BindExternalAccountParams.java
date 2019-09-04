package io.hayk.demo.note.user;

public interface BindExternalAccountParams {

    String email();

    String externalAccountUid();

    String providerName();

    static BindExternalAccountParams of(final String email,final String externalAccountUid,final String providerName){
        return new ImmutableBindExternalAccountParams(email,externalAccountUid,providerName);
    }
}
