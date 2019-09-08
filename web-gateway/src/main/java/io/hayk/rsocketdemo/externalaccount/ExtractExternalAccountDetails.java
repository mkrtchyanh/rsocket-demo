package io.hayk.rsocketdemo.externalaccount;

public interface ExtractExternalAccountDetails {

    String uid();

    String email();

    String provider();

    static ExtractExternalAccountDetails of(final String uid, final String email, final String provider) {
        return new ExtractExternalAccountDetails() {
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
