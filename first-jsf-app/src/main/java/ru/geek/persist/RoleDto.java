package ru.geek.persist;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class RoleDto {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    public RoleDto() {
    }

    public RoleDto(Role r) {
        this.id = r.getId();
        this.name = r.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return name.equals(roleDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
