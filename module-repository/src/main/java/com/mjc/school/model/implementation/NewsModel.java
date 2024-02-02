package com.mjc.school.model.implementation;

import com.fasterxml.jackson.annotation.*;
import com.mjc.school.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news")
@Entity
public class NewsModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30,  nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @LastModifiedDate
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel author;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="news_tags", joinColumns = @JoinColumn(name = "news_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagModel> tag = new ArrayList<>();
}
