package com.example.test.service;

import java.util.List;

public interface ParentService<T> {

    T save(T t);

    List<T> getAll();

    void delete(T t);
}
