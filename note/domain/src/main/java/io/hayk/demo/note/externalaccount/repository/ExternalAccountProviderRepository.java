package io.hayk.demo.note.externalaccount.repository;

import io.hayk.demo.note.externalaccount.ExternalAccountProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalAccountProviderRepository extends JpaRepository<ExternalAccountProvider, Long> {

    Optional<ExternalAccountProvider> findByName(String name);

}
