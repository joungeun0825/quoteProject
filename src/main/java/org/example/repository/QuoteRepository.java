package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.domain.Quote;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuoteRepository {
    @PersistenceContext
    private final EntityManager em;

    public void save(Quote quote) {
        em.persist(quote);
    }
}
