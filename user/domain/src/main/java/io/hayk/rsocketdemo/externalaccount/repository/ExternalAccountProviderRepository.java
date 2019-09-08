package io.hayk.rsocketdemo.externalaccount.repository;

import io.hayk.rsocketdemo.externalaccount.ExternalAccountProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalAccountProviderRepository extends JpaRepository<ExternalAccountProvider, Long> {

    Optional<ExternalAccountProvider> findByName(final String name);
}
