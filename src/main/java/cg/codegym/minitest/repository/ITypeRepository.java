package cg.codegym.minitest.repository;

import cg.codegym.minitest.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface ITypeRepository extends CrudRepository<Type, Long> {
}
