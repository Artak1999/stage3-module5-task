package com.mjc.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDtoRequest {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private final List<Long> tagIds = new ArrayList<>();
}
