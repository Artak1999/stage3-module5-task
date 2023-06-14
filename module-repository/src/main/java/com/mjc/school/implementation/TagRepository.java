package com.mjc.school.implementation;

import com.mjc.school.BaseRepository;
import com.mjc.school.model.implementation.TagModel;
import org.springframework.stereotype.Repository;

import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<TagModel,Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<TagModel> readAll(int size, int page, String sort) {
        return entityManager.createQuery("Select a From TagModel a").getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(TagModel.class, id));
    }

    @Override
    public TagModel create(TagModel entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        entityManager.getTransaction().begin();
        TagModel update = entityManager.getReference(TagModel.class, entity.getId());
        update.setName(entity.getName());
        entityManager.getTransaction().commit();
        return update;
    }

    @Override
    public boolean deleteById(Long id) {
        entityManager.getTransaction().begin();
        boolean delete = entityManager.createQuery("Delete From TagModel t Where t.id = :id").setParameter("id", id).executeUpdate() != 0;
        entityManager.getTransaction().commit();
        return delete;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
