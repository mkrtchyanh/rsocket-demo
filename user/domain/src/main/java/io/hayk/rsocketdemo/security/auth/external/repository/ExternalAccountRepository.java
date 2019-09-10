package io.hayk.rsocketdemo.security.auth.external.repository;

import io.hayk.rsocketdemo.security.auth.external.ExternalAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalAccountRepository extends JpaRepository<ExternalAccount, Long> {

    Optional<ExternalAccount> findByUidAndProviderName(final String uid, final String providerName);
}
