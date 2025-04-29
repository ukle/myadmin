package me.star.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@Slf4j
public class BaseServiceImpl<R extends BaseRepository<T, K>, T, K> implements IBaseService<T, K> {

    @Autowired
    protected R repository;

    public BaseServiceImpl() {
    }

    @Override
    public T getOne(K id) {
        return this.repository.getOne(id);
    }

    @Override
    public Optional<T> findOne(Example<T> example) {
        return this.repository.findOne(example);
    }

    @Override
    public Optional<T> findById(K id) {
        return this.repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<T> findAllById(Iterable<K> idList) {
        return this.repository.findAllById(idList);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public Page<T> findAll(Specification<T> specification, Pageable pageable) {
        return this.repository.findAll(specification, pageable);
    }

    @Override
    public List<T> findAll(Specification<T> specification) {
        return this.repository.findAll(specification);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return this.repository.findAll(sort);
    }

    @Override
    public List<T> findAll(Example<T> example) {
        return this.repository.findAll(example);
    }

    @Override
    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return this.repository.findAll(example, pageable);
    }

    @Override
    public List<T> findAll(Example<T> example, Sort sort) {
        return this.repository.findAll(example, sort);
    }

    @Override
    public T save(T t) {
        return this.repository.save(t);
    }

    @Override
    public T saveAndFlush(T t) {
        return this.repository.saveAndFlush(t);
    }

    @Override
    public List<T> saveAll(Iterable<T> list) {
        return this.repository.saveAll(list);
    }

    @Override
    public void deleteById(K id) {
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

    @Override
    public void delete(T t) {
        this.repository.delete(t);
    }

    @Override
    public boolean existsById(K id) {
        return this.repository.existsById(id);
    }

    @Override
    public long count() {
        return this.repository.count();
    }

    @Override
    public long count(Example<T> example) {
        return this.repository.count(example);
    }

    @Override
    public void flush() {
        this.repository.flush();
    }

}
