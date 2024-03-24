package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.domain.Comment;
import org.example.domain.Quote;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    @PersistenceContext
    private final EntityManager em;

    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }
}
