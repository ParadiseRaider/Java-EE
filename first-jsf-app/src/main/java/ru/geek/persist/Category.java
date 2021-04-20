package ru.geek.persist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@NamedQueries({
@NamedQuery(name = "deleteCategoryById", query = "delete from Category c where c.id = :id"),
@NamedQuery(name = "findAllCategory", query = "from Category c"),
@NamedQuery(name = "countCategory", query = "select count(c) from Category c")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
