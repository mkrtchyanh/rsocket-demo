package io.hayk.rsocketdemo.security.auth.external;

public interface ExternalAccountDetails {

    String uid();

    String email();

    String provider();

    static ExternalAccountDetails of(final String uid, final String email, final String provider) {
        return new ExternalAccountDetails() {
            @Override
            public String uid() {
                return uid;
            }

            @Override
            public String email() {
                return email;
            }

            @Override
            public String provider() {
                return provider;
            }
        };
    }
}
