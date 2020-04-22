package com.example.test.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ParentService<T> {

    T save(T t);

    List<T> getAll();

    void delete(T t);
}
