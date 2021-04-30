package ru.geek.persist;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class UserDto {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private Set<RoleDto> roles;

    public UserDto() {
    }

    public UserDto(User customer) {
        this.id = customer.getId();
        this.age = customer.getAge();
        this.login = customer.getLogin();
        this.password = customer.getPassword();
        this.roles = new HashSet<>();
        customer.getRoles().forEach(r -> this.roles.add(new RoleDto(r)));
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id="+id+", "
                +"age="+age+", "
                +"login="+login+'\''
                +'}';
    }
}
