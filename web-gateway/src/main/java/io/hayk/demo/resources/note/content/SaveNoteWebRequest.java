package io.hayk.demo.resources.note.content;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public abstract class SaveNoteWebRequest {

    @NotEmpty
    @Length(max = 50)
    private String title;

    @NotEmpty
    @Length(max = 1000)
    private String text;


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
