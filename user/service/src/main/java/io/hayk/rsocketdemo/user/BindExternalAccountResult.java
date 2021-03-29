package io.hayk.rsocketdemo.user;

public interface BindExternalAccountResult {

    Long userId();

    static BindExternalAccountResult of(final Long userId) {
        return new ImmutablBindExternalAccountResult(userId);
    }
}
