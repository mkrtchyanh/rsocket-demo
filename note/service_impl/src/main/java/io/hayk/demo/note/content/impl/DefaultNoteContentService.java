package io.hayk.demo.note.content.impl;

import io.hayk.demo.note.content.CreateNoteContentParam;
import io.hayk.demo.note.content.NoteContent;
import io.hayk.demo.note.content.NoteContentService;
import io.hayk.demo.note.content.UpdateNoteContentParam;
import io.hayk.demo.note.content.repository.NoteContentRepository;
import io.hayk.demo.note.user.User;
import io.hayk.demo.note.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

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
    public NoteContent createNoteContent(final CreateNoteContentParam param) {
        assertValidCreateNoteContentParam(param);
        final User creator = userService.getUserById(param.userId());
        return noteContentRepository.save(new NoteContent(creator, param.title(), param.text()));
    }

    @Override
    @Transactional
    public NoteContent updateNoteContent(final UpdateNoteContentParam param) {
        assertValidUpdateNoteContentParam(param);
        final NoteContent content = noteContentRepository.getOne(param.noteId());
        content.changeText(param.text());
        content.changeTitle(param.title());
        return noteContentRepository.save(content);
    }

    @Override
    @Transactional(readOnly = true)
    public NoteContent getNoteContent(final Long id) {
        Assert.notNull(id, "Null was provided as ana argument for parameter 'id'.");
        return noteContentRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteContent> getUserNotes(final Long userId) {
        Assert.notNull(userId, "Null was provided as ana argument for parameter 'userId'.");
        return noteContentRepository.findByCreatedById(userId);
    }

    private void assertValidCreateNoteContentParam(final CreateNoteContentParam param) {
        Assert.notNull(param, "Null was provided as ana argument for parameter 'param'.");
    }

    private void assertValidUpdateNoteContentParam(final UpdateNoteContentParam param) {
        Assert.notNull(param, "Null was provided as ana argument for parameter 'param'.");
    }
}
