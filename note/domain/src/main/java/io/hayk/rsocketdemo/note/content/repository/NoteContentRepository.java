package io.hayk.rsocketdemo.note.content.repository;

import io.hayk.rsocketdemo.note.content.NoteContent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteContentRepository extends JpaRepository<NoteContent, Long> {


    List<NoteContent> findByCreatedById(final Long userId);

    @Query("select n from NoteContent n where n.id = :id")
    @EntityGraph("NoteContent.User")
    NoteContent findByIdWithFetchingUser(@Param("id") final Long id);

}
