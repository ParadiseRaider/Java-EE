package ru.geek.service;

import ru.geek.persist.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@PermitAll
public class UserServiceImpl {

    @EJB
    private UserRepository userRepository;


    @RolesAllowed({"ADMIN"})
    public void save(UserDto userDto) {
        userRepository.save(new User(userDto));
    }

    @RolesAllowed({"ADMIN"})
    public void delete(Long id) {
        userRepository.delete(id);
    }

    public UserDto findById(Long id) {
        return new UserDto(userRepository.findById(id));
    }

    public List<UserDto> findAllWithRoleFetch() {
        return userRepository.findAllWithRoleFetch().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
