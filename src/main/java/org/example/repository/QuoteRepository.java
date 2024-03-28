package org.example.repository;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuoteRepository {
    @PersistenceContext
    private final EntityManager em;

    public Quote save(Quote quote) {
        em.persist(quote);
        return quote;
    }
    public void delete(Long quoteId) {
        em.createNativeQuery("DELETE FROM Quote WHERE quote_id = :quoteId")
                .setParameter("quoteId", quoteId)
                .executeUpdate();
    }

    public List<Quote> findAll(Book book){
        return em.createQuery("select q from Quote q where q.book=:book", Quote.class)
                .setParameter("book",book)
                .getResultList();
    }
    public List<Quote> findAllQuotes(){
        return em.createQuery("select q from Quote q", Quote.class)
                .getResultList();
    }
    public List<Quote> findByContent(String content,Book book){
        List<Quote> result = em.createQuery(
                "SELECT q FROM Quote q where q.content like :content AND q.book=:book", Quote.class)
                .setParameter("content", content)
                .setParameter("book", book)
                .getResultList();
        return result;
    }
    public List<Quote> findByPage(Integer page,Book book){
        List<Quote> result = em.createQuery("select q from Quote q where q.page=:page AND q.book=:book", Quote.class)
                .setParameter("page", page)
                .setParameter("book", book)
                .getResultList();
        return result;
    }
    public Optional<Quote> selectQuote(Long quoteId) {
        try {
            Quote result = em.createQuery("select q from Quote q where q.id=:quoteId", Quote.class)
                    .setParameter("quoteId", quoteId)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            // Handle non-unique result case if needed
            return Optional.empty();
        }
    }
}
