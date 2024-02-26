package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuoteRepository {
    @PersistenceContext
    private final EntityManager em;

    public void save(Quote quote) {
        em.persist(quote);
    }
    public List<Quote> findAll(){
        return em.createQuery("select q from Quote q", Quote.class)
                .getResultList();
    }
    public List<Quote> findByContent(String content){
        List<Quote> result = em.createQuery(
                "SELECT q FROM Quote q where q.content like :content ", Quote.class)
                .setParameter("content", content)
                .getResultList();
        return result;
    }
    public List<Quote> findByPage(Integer page){
        List<Quote> result = em.createQuery("select q from Quote q where q.page=:page", Quote.class)
                .setParameter("page", page)
                .getResultList();
        return result;
    }
}
