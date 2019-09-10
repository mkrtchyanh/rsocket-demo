package io.hayk.rsocketdemo;

public interface BindExternalAccountResult {

    Long userId();

    static BindExternalAccountResult of(final Long userId) {
        return new ImmutablBindExternalAccountResult(userId);
    }
}
