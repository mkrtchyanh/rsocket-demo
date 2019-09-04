package io.hayk.demo.resources.note.content;

import io.hayk.demo.note.content.SaveNoteRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


class SaveNoteWebRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return SaveNoteRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "title", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "title", "field.required");
        final SaveNoteRequest request = (SaveNoteRequest) target;
    }
}
