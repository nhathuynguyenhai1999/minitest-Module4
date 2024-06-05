package cg.codegym.minitest.Service.iml;

import cg.codegym.minitest.model.Computer;
import cg.codegym.minitest.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IComputerService extends IGenerateService<Computer> {
    Iterable<Computer> findAllByType(Type type);
    Page<Computer> findAll(Pageable pageable);
    Page<Computer> findAllByNameContaining(Pageable pageable, String name);
}
