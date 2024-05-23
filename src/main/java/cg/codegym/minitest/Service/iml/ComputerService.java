package cg.codegym.minitest.Service.iml;

import cg.codegym.minitest.Model.Computer;
import cg.codegym.minitest.Model.Type;
import cg.codegym.minitest.Repository.IComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ComputerService implements IComputerService {
    @Autowired
    private IComputerRepository iComputerRepository;

    @Override
    public Iterable<Computer> findAll() {
        return iComputerRepository.findAll();
    }

    @Override
    public void save(Computer customer) {
        iComputerRepository.save(customer);
    }

    @Override
    public Optional<Computer> findById(Long id) {
        return iComputerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iComputerRepository.deleteById(id);
    }

    @Override
    public Iterable<Computer> findAllByType(Type type) {
        return iComputerRepository.findAllByType(type);
    }

    @Override
    public Page<Computer> findAll(Pageable pageable) {
        return iComputerRepository.findAll(pageable);
    }

    @Override
    public Page<Computer> findAllByNameContaining(Pageable pageable, String name) {
        return iComputerRepository.findAllByNameContaining(pageable, name);
    }
}
