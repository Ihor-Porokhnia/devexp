package com.example.test.service;

import com.example.test.dao.TestRepository;
import com.example.test.model.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService implements ParentService<TestEntity> {

    @Autowired
    private TestRepository repository;

    @Override
    @Transactional
    public TestEntity save(TestEntity entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public List<TestEntity> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(TestEntity entity) {
        repository.delete(entity);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public TestEntity getOne(Integer id) {
        return repository.getOne(id);
    }
}
