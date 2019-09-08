package io.hayk.rsocketdemo.resources.common;

import java.util.Collections;
import java.util.List;

public class ValidationFailedException extends RuntimeException {

    private final List<String> errors;

    public ValidationFailedException(final List<String> errors) {
        this.errors = Collections.unmodifiableList(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}
