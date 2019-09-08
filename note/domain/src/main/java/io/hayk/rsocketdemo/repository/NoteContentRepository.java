package io.hayk.rsocketdemo.repository;

import io.hayk.rsocketdemo.NoteContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteContentRepository extends JpaRepository<NoteContent, Long> {

    List<NoteContent> findByUserId(final Long userId);

}
