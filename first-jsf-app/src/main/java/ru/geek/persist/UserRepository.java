package ru.geek.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class UserRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    public void save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        }
        em.merge(user);
    }

    @TransactionAttribute
    public void delete(Long id) {
        em.createNamedQuery("deleteUserById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createNamedQuery("findAllUsers", User.class).getResultList();
    }

    public List<User> findAllWithRoleFetch() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> from = query.from(User.class);
        from.fetch("roles", JoinType.LEFT);
        query.select(from).distinct(true);
        return em.createQuery(query).getResultList();
    }

    public long count() {
        return em.createNamedQuery("countUser", Long.class).getSingleResult();
    }
}
