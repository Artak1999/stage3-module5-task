package com.mjc.school.model.implementation;

import com.mjc.school.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag")
@Entity
public class TagModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15, unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    List<NewsModel> news = new ArrayList<>();
}
