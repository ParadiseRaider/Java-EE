package ru.geek.persist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;

    @NotNull
    private String name;

    @Positive
    private Integer age;
}
