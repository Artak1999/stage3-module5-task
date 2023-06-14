package com.mjc.school;

import java.util.List;

public interface BaseService<T, R, K> {
    List<R> readAll(int size, int page, String sort);

    R readById(K id);

    R create(T createRequest);

    R update(T updateRequest);

    boolean deleteById(K id);
}
