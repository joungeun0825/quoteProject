package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.dto.AddBookRequest;
import org.example.dto.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    @PersistenceContext
    private final EntityManager em;

    public void save(Book book){
        em.persist(book);
    }
    public List<Book> findAll(){
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
    public List<Book> findByTitle(String title){
        List<Book> result = em.createQuery("select b from Book b where b.title=:title", Book.class)
                .setParameter("title", title)
                .getResultList();
        return result;
    }
    public List<Book> findByAuthor(String author){
        List<Book> result = em.createQuery("select b from Book b where b.author=:author", Book.class)
                .setParameter("author", author)
                .getResultList();
        return result;
    }
    public List<Book> findByPublisher(String publisher){
        List<Book> result = em.createQuery("select b from Book b where b.publisher=:publisher", Book.class)
                .setParameter("publisher", publisher)
                .getResultList();
        return result;
    }

}