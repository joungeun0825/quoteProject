package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.domain.Quote;
import org.example.domain.QuoteTag;
import org.example.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepository {
    @PersistenceContext
    private final EntityManager em;
    public void save(Tag tag){
        em.persist(tag);
    }
    public Optional<Tag> selectTag(Long tagId) {
        try {
            Tag result = em.createQuery("select t from Tag t where t.id =:tagId", Tag.class)
                    .setParameter("tagId", tagId)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            // Handle non-unique result case if needed
            return Optional.empty();
        }
    }
    public List<Tag> findAllTags() {
        return em.createQuery("select t from Tag t", Tag.class)
                .getResultList();
    }
}
