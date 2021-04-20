package ru.geek.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CustomerRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    public void save(Customer customer) {
        if (customer.getId() == null) {
            em.persist(customer);
        }
        em.merge(customer);
    }

    @TransactionAttribute
    public void delete(Long id) {
        em.createNamedQuery("deleteCustomerById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        return em.createNamedQuery("findAllCustomer", Customer.class).getResultList();
    }

    public long count() {
        return em.createNamedQuery("countCustomer", Long.class).getSingleResult();
    }
}
