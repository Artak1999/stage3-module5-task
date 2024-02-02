package com.mjc.school.implementation;

import com.mjc.school.BaseRepository;
import com.mjc.school.OnDelete;
import com.mjc.school.model.implementation.AuthorModel;
import com.mjc.school.model.implementation.NewsModel;
import org.springframework.stereotype.Repository;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel,Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<NewsModel> readAll(int size, int page, String sort) {
        return entityManager.createQuery("Select a From NewsModel a").getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(NewsModel.class, id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        entityManager.getTransaction().begin();
        NewsModel update = entityManager.getReference(NewsModel.class, entity.getId());
        update.setAuthor(entity.getAuthor());
        update.setTag(entity.getTag());
        update.setTitle(entity.getTitle());
        update.setContent(entity.getContent());
        entityManager.getTransaction().commit();
        return update;
    }

    @Override
    @OnDelete
    public boolean deleteById(Long id) {
        entityManager.getTransaction().begin();
        boolean delete = entityManager.createQuery("Delete From NewsModel n Where n.id = :id").setParameter("id", id).executeUpdate() != 0;
        entityManager.getTransaction().commit();
        return delete;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }

    public long countNews() {
        return (long) entityManager.createQuery("select count(*) from NewsModel").getSingleResult();
    }

    public List<AuthorModel> getAuthor() {
        return entityManager.createQuery("select a from AuthorModel a", AuthorModel.class).getResultList();
    }

    public List<NewsModel> getAll(){
        return entityManager.createQuery("select n from NewsModel n", NewsModel.class).getResultList();
    }
}
