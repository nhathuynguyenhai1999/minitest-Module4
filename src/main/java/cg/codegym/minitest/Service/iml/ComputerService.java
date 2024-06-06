package cg.codegym.minitest.Service.iml;

import cg.codegym.minitest.exception.DuplicateProductCodeException;
import cg.codegym.minitest.model.Computer;
import cg.codegym.minitest.model.Type;
import cg.codegym.minitest.repository.IComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        if (iComputerRepository.existsByCode(customer.getCode())) {
            throw new DuplicateProductCodeException("Product code already exists: " + customer.getCode());
        }
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
    public Computer findOne(Long id) throws Exception {
        Computer computer = new Computer();
        if(computer.getName() == null){
            throw new Exception("Computer not found");
        }
        return computer;
    }
    @Override
    public Page<Computer> findAllByNameContaining(Pageable pageable, String name) {
        return iComputerRepository.findAllByNameContaining(pageable, name);
    }
    //fix
    @Override
    public Computer findByID(Long id){
        return iComputerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Computer not found with ID: " + id));
    }
}
