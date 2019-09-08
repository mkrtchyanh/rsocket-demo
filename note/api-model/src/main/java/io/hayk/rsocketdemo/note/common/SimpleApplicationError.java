package io.hayk.rsocketdemo.note.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SimpleApplicationError implements ApplicationError {

    private String message;

    public SimpleApplicationError(final String message) {
        this.message = message;
    }

    public SimpleApplicationError() {
        super();
    }

    @Override
    public String textValue() {
        return StringUtils.trimToEmpty(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SimpleApplicationError that = (SimpleApplicationError) o;
        return new EqualsBuilder()
                .append(message, that.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(message)
                .toHashCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
