package cg.codegym.minitest.Service.iml;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    T save(T t);

    Optional<T> findById(Long id);

    void remove(Long id);
}
