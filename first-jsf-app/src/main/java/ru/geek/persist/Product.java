package ru.geek.persist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "findAllProduct", query = "from Product p"),
        @NamedQuery(name = "findAllWithCategoryFetch", query = "select p from Product p left join fetch p.category"),
        @NamedQuery(name = "findAllProductWithCategoryId", query = "select  p from Product p left join p.category where p.category.id = :id"),
        @NamedQuery(name = "findByProductName", query = "select p from Product p where p.name = :name"),
        @NamedQuery(name = "countProduct", query = "select count(p) from Product p")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String description;

    @Column
    @Positive
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
