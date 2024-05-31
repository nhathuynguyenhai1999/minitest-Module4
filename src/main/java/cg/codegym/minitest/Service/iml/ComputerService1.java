package cg.codegym.minitest.Service.iml;

import cg.codegym.minitest.Model.Computer;
import cg.codegym.minitest.Model.Type;
import cg.codegym.minitest.Model.dto.ComputerDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComputerService1 implements UserDetailsService {
    public static List<Computer> listUser = new ArrayList<>();
    public static List<Type> listRole = new ArrayList<>();

    public ComputerService1() {
        listRole.add(new Type(1L, "ROLE_ADMIN"));
        listRole.add(new Type(2L, "ROLE_USER"));

        String password = "$2a$10$xMq9EwZvdKUuvgiaM2T1Iuw9A1EGXVZaCIUPEwn1Isa9ffvPqNabe"; // Example hashed password
        Computer userKai = new Computer(1L, "kai", password);
        userKai.setType(listRole.get(0));

        Computer userSena = new Computer(2L, "sena", password);
        userSena.setType(listRole.get(1));

        listUser.add(userKai);
        listUser.add(userSena);
    }

    public List<ComputerDTO> findAll() {
        return listUser.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ComputerDTO findById(Long id) {
        return listUser.stream().filter(user -> Objects.equals(user.getId(), id)).map(this::toDTO).findFirst().orElse(null);
    }

    public Computer findByUsername(String username) {
        return listUser.stream().filter(user -> Objects.equals(user.getName(), username)).findFirst().orElse(null);
    }

    public boolean add(Computer user) {
        boolean userExists = listUser.stream().anyMatch(userExist ->
                Objects.equals(user.getId(), userExist.getId()) || Objects.equals(user.getName(), userExist.getName()));
        if (userExists) {
            return false;
        }
        listUser.add(user);
        return true;
    }

    public void delete(Long id) {
        listUser.removeIf(user -> Objects.equals(user.getId(), id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Computer computer = findByUsername(username);
        if (computer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(computer.getType().getName());
        return new User(computer.getName(), computer.getCode(), Collections.singleton(authority));
    }

    private ComputerDTO toDTO(Computer computer) {
        ComputerDTO dto = new ComputerDTO();
        dto.setId(computer.getId());
        dto.setName(computer.getName());
        dto.setCode(computer.getCode());
        dto.setTypes(Collections.singleton(computer.getType().getName()));
        return dto;
    }
}
