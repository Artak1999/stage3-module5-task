package com.mjc.school.implementation;

import com.mjc.school.model.implementation.AuthorModel;
import com.mjc.school.BaseRepository;
import com.mjc.school.OnDelete;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel,Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings({"unchecked"})
    public List<AuthorModel> readAll(int size, int page, String sort) {
        return entityManager.createQuery("Select a From AuthorModel a").getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(AuthorModel.class, id));
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        entityManager.getTransaction().begin();
        AuthorModel update = entityManager.getReference(AuthorModel.class, entity.getId());
        update.setName(entity.getName());
        entityManager.getTransaction().commit();
        return update;
    }

    @Override
    @OnDelete
    public boolean deleteById(Long id) {
        entityManager.getTransaction().begin();
        boolean delete = entityManager.createQuery("Delete From AuthorModel a Where a.id = :id").setParameter("id", id).executeUpdate() != 0;
        entityManager.getTransaction().commit();
        return delete;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
