package io.hayk.rsocketdemo.note.impl;

import io.hayk.rsocketdemo.*;
import io.hayk.rsocketdemo.note.*;
import io.hayk.rsocketdemo.user.repository.NoteContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
class DefaultUserNoteService implements UserNoteService {

    private final NoteContentRepository noteContentRepository;

    DefaultUserNoteService(final NoteContentRepository noteContentRepository) {
        this.noteContentRepository = noteContentRepository;
    }

    @Override
    @Transactional
    public UserNote createUserNote(final CreateUserNoteParam param) {
        assertValidCreateNoteContentParam(param);
        return ImmutableUserNote.of(noteContentRepository.save(new NoteContent(param.userId(), param.title(), param.text())));
    }

    @Override
    @Transactional
    public UserNote updateUserNote(final UpdateUserNoteParam param) {
        assertValidUpdateNoteContentParam(param);
        final NoteContent content = noteContentRepository.getOne(param.noteId());
        if (!param.userId().equals(content.getUserId())) {
            throw new IllegalArgumentException(
                    format("User with id %d is not allowed to update note with id %d",
                            param.userId(),
                            content.getUserId())
            );
        }
        content.changeText(param.text());
        content.changeTitle(param.title());
        return ImmutableUserNote.of(noteContentRepository.save(content));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserNote> getUserNote(final Long id) {
        Assert.notNull(id, "Null was provided as ana argument for parameter 'id'.");
        return noteContentRepository.findById(id)
                .map(ImmutableUserNote::of);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserNote> getUserNotes(final Long userId) {
        Assert.notNull(userId, "Null was provided as ana argument for parameter 'userId'.");
        return noteContentRepository.findByUserId(userId).stream()
                .map(ImmutableUserNote::of)
                .collect(Collectors.toList());
    }

    private static void assertValidCreateNoteContentParam(final CreateUserNoteParam param) {
        assertValidBaseBaseSaveNoteContentParam(param);
    }

    private static void assertValidUpdateNoteContentParam(final UpdateUserNoteParam param) {
        assertValidBaseBaseSaveNoteContentParam(param);
        Assert.notNull(param.noteId(), "Null was provided as ana argument for parameter 'param.noteId'.");
    }

    private static void assertValidBaseBaseSaveNoteContentParam(final BaseSaveNoteContentParam param) {
        Assert.notNull(param, "Null was provided as ana argument for parameter 'param'.");
        Assert.notNull(param.userId(), "Null was provided as ana argument for parameter 'param.userId'.");
        Assert.hasText(param.title(), "Null or empty text was provided as ana argument for parameter 'param.title'.");
        Assert.hasText(param.text(), "Null  or empty text was provided as ana argument for parameter 'param.text'.");
    }

}
