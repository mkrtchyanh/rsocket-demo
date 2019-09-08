package io.hayk.rsocketdemo.note.user.repository;

import io.hayk.rsocketdemo.note.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(final String email);
}
