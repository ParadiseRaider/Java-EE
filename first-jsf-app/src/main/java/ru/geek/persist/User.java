package ru.geek.persist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "deleteUserById", query = "delete from User c where c.id = :id"),
        @NamedQuery(name = "findAllUsers", query = "from User u"),
        @NamedQuery(name = "findAllWithRoleFetch", query = "select u from User u left join fetch u.roles"),
        @NamedQuery(name = "countUser", query = "select count(u) from User u")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private Integer age;

    @Column
    @Getter
    @Setter
    private String login;

    @Column
    @Getter
    @Setter
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter
    @Setter
    private Set<Role> roles;

    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.age = userDto.getAge();
        this.login = userDto.getLogin();
        this.password = userDto.getPassword();
        this.roles = new HashSet<>();
        userDto.getRoles().forEach(r -> roles.add(new Role(r)));
    }
}
