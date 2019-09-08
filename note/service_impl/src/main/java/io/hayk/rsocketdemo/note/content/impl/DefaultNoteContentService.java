package io.hayk.rsocketdemo.note.content.impl;

import io.hayk.rsocketdemo.note.content.*;
import io.hayk.rsocketdemo.note.content.repository.NoteContentRepository;
import io.hayk.rsocketdemo.note.user.User;
import io.hayk.rsocketdemo.note.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
class DefaultNoteContentService implements NoteContentService {

    private final NoteContentRepository noteContentRepository;

    private final UserService userService;

    DefaultNoteContentService(final NoteContentRepository noteContentRepository, final UserService userService) {
        this.noteContentRepository = noteContentRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserNote createNoteContent(final CreateNoteContentParam param) {
        assertValidCreateNoteContentParam(param);
        final User creator = userService.getUserById(param.userId());
        return UserNote.of(noteContentRepository.save(new NoteContent(creator, param.title(), param.text())));
    }

    @Override
    @Transactional
    public UserNote updateNoteContent(final UpdateNoteContentParam param) {
        assertValidUpdateNoteContentParam(param);
        final NoteContent content = noteContentRepository.getOne(param.noteId());
        if (!param.userId().equals(content.getCreatedBy().getId())) {
            throw new IllegalArgumentException(
                    format("User with id %d is not allowed to update note with id %d",
                            param.userId(),
                            content.getCreatedBy().getId())
            );
        }
        content.changeText(param.text());
        content.changeTitle(param.title());
        return UserNote.of(noteContentRepository.save(content));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserNote> getUserNote(final Long id) {
        Assert.notNull(id, "Null was provided as ana argument for parameter 'id'.");
        return noteContentRepository.findById(id)
                .map(UserNote::of);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserNote> getUserNotes(final Long userId) {
        Assert.notNull(userId, "Null was provided as ana argument for parameter 'userId'.");
        return noteContentRepository.findByCreatedById(userId).stream()
                .map(UserNote::of)
                .collect(Collectors.toList());
    }

    private static void assertValidCreateNoteContentParam(final CreateNoteContentParam param) {
        Assert.notNull(param, "Null was provided as ana argument for parameter 'param'.");
    }

    private static void assertValidUpdateNoteContentParam(final UpdateNoteContentParam param) {
        Assert.notNull(param, "Null was provided as ana argument for parameter 'param'.");
    }
}
