package io.hayk.rsocketdemo.note.externalaccount.repository;

import io.hayk.rsocketdemo.note.externalaccount.ExternalAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalAccountRepository extends JpaRepository<ExternalAccount, Long> {

    Optional<ExternalAccount> findByUidAndProviderName(final String uid, final String providerName);
}
