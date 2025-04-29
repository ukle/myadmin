
package me.star.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T, K> {

    T getOne(K id);

    Optional<T> findOne(Example<T> example);

    Optional<T> findById(K id);

    List<T> findAll();

    List<T> findAllById(Iterable<K> idList);

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(Specification<T> specification, Pageable pageable);

    List<T> findAll(Specification<T> specification);

    List<T> findAll(Sort sort);

    List<T> findAll(Example<T> example);

    Page<T> findAll(Example<T> example, Pageable pageable);

    List<T> findAll(Example<T> example, Sort sort);

    T save(T t);

    T saveAndFlush(T t);

    List<T> saveAll(Iterable<T> list);

    void deleteById(K id);

    void deleteAll();

    void delete(T t);

    boolean existsById(K id);

    long count();

    long count(Example<T> example);

    void flush();
}
