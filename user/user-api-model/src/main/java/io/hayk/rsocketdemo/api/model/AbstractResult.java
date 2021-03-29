package io.hayk.rsocketdemo.api.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractResult {

    private List<String> errors;

    public AbstractResult() {
        super();
    }

    public AbstractResult(final List<String> errors) {
        this.errors = errors;
    }

    public static <R extends  AbstractResult> R withErros(final Supplier<R> resultSupplier,
                                                          final List<String> errors){
        final R result = resultSupplier.get();
        result.setErrors(errors);
        return result;
    }

    public List<String> getErrors() {
        return errors == null ? Collections.emptyList() : errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errors", errors)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AbstractResult that = (AbstractResult) o;
        return new EqualsBuilder()
                .append(errors, that.errors)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(errors)
                .toHashCode();
    }
}
