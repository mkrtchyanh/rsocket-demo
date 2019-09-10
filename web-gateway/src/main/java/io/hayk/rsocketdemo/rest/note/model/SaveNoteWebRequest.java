package io.hayk.rsocketdemo.rest.note.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public abstract class SaveNoteWebRequest {

    @NotEmpty
    @Length(max = 50)
    @JsonProperty
    private String title;

    @NotEmpty
    @Length(max = 1000)
    @JsonProperty
    private String text;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("text", text)
                .toString();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
