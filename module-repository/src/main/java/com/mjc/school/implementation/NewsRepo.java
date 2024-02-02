package com.mjc.school.implementation;

import com.mjc.school.model.implementation.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<NewsModel,Long> {
}
