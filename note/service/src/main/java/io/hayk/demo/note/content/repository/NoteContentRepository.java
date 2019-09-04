package io.hayk.demo.note.content.repository;

import io.hayk.demo.note.content.NoteContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteContentRepository extends JpaRepository<NoteContent, Long> {

    List<NoteContent> findByCreatedById(Long userId);

}
