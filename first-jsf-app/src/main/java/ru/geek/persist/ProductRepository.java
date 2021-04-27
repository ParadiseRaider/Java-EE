package ru.geek.persist;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    public void delete(Long id) {
        em.createNamedQuery("deleteProductById")
                .setParameter("id",id)
                .executeUpdate();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public Product findByName(String name) {
        return em.createNamedQuery("findByProductName", Product.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Product> findAllProductWithCategoryId(Long id) {
        return em.createNamedQuery("findAllProductWithCategoryId", Product.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Product> findAll() {
        return em.createNamedQuery("findAllProduct", Product.class).getResultList();
    }

    public List<Product> findAllWithCategoryFetch() {
        return em.createNamedQuery("findAllWithCategoryFetch", Product.class)
                .getResultList();
    }

    public long count() {
        return em.createNamedQuery("countProduct", Long.class).getSingleResult();
    }
}
