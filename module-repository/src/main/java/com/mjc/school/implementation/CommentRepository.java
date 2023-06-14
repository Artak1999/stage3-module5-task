package com.mjc.school.implementation;

import com.mjc.school.BaseRepository;
import com.mjc.school.model.implementation.CommentModel;
import org.springframework.stereotype.Repository;

import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepository implements BaseRepository<CommentModel,Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings({"unchecked"})
    public List<CommentModel> readAll(int size, int page, String sort) {
        return entityManager.createQuery("Select a From CommentModel a").getResultList();
    }

    @Override
    public Optional<CommentModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(CommentModel.class, id));
    }

    @Override
    public CommentModel create(CommentModel entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public CommentModel update(CommentModel entity) {
        entityManager.getTransaction().begin();
        CommentModel update = entityManager.getReference(CommentModel.class, entity.getId());
        update.setContent(entity.getContent());
        entityManager.getTransaction().commit();
        return update;
    }

    @Override
    public boolean deleteById(Long id) {
        entityManager.getTransaction().begin();
        boolean delete = entityManager.createQuery("Delete From CommentModel a Where a.id = :id").setParameter("id", id).executeUpdate() != 0;
        entityManager.getTransaction().commit();
        return delete;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
