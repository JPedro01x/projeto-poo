package com.example.dao;

import com.example.model.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
public class PedidoDAO {
    private final EntityManagerFactory emf;

    public PedidoDAO(EntityManagerFactory emf) { this.emf = emf; }

    public Pedido save(Pedido p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally { em.close(); }
    }

    public Pedido findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try { return em.find(Pedido.class, id); } finally { em.close(); }
    }

    public List<Pedido> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Pedido> q = em.createQuery("SELECT p FROM Pedido p", Pedido.class);
            return q.getResultList();
        } finally { em.close(); }
    }

    public Pedido update(Pedido p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pedido merged = em.merge(p);
            em.getTransaction().commit();
            return merged;
        } finally { em.close(); }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pedido p = em.find(Pedido.class, id);
            if (p != null) em.remove(p);
            em.getTransaction().commit();
        } finally { em.close(); }
    }
}
