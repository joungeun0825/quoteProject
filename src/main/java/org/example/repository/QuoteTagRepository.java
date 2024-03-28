package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.domain.QuoteTag;
import org.example.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuoteTagRepository {
    @PersistenceContext
    private final EntityManager em;
    public void save(QuoteTag quoteTag){
        em.persist(quoteTag);
    }
    public Optional<QuoteTag> selectQuoteTag(Long quoteTagId) {
        try {
            QuoteTag result = em.createQuery("select qt from QuoteTag qt where qt.id =:quoteTagId", QuoteTag.class)
                    .setParameter("quoteTagId", quoteTagId)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            // Handle non-unique result case if needed
            return Optional.empty();
        }
    }

    public Optional<List<QuoteTag>> selectAllTag() {
        try {
            List<QuoteTag> result = em.createQuery("select qt from QuoteTag qt", QuoteTag.class)
                    .getResultList();

            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            // Handle non-unique result case if needed
            return Optional.empty();
        }

    }

    public Optional<List<QuoteTag>> selectQuotes(Long randomTagId) {
        try {
            List<QuoteTag> quoteTags = em.createQuery("select qt from QuoteTag qt where qt.tag.id =:randomTagId", QuoteTag.class)
                    .setParameter("randomTagId", randomTagId)
                    .getResultList();
            return Optional.of(quoteTags);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            // Handle non-unique result case if needed
            return Optional.empty();
        }

    }
}
