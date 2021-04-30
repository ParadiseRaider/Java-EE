package ru.geek.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class RoleRepository {

    @PersistenceContext(unitName = "ds")
    protected EntityManager em;

    @TransactionAttribute
    public Role merge(Role role) {
        return em.merge(role);
    }

    public Role findById(Long id) {
        return em.find(Role.class, id);
    }

    public List<Role> getAllRoles() {
        return em.createNamedQuery("findAllRole", Role.class).getResultList();
    }
}
