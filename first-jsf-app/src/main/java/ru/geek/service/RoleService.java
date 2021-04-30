package ru.geek.service;

import ru.geek.persist.RoleDto;
import ru.geek.persist.RoleRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleService implements Serializable {

    @EJB
    private RoleRepository roleRepository;

    public List<RoleDto> getAllRoles() {
        return roleRepository.getAllRoles().stream()
                .map(RoleDto::new)
                .collect(Collectors.toList());
    }
}
