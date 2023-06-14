package com.mjc.school.controller;

import java.util.List;

public interface BaseControllers<T, R, K> {
    Object create(T request);
    Object readById(K id);
    List<R> readAll(int size, int page, String sort);
    Object update(K id, T request);
    void deleteById(K id);
}
