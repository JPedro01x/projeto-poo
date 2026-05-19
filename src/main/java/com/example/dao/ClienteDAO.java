package com.example.dao;

import com.example.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDAO {
    private final EntityManagerFactory emf;

    public ClienteDAO(EntityManagerFactory emf) { this.emf = emf; }

    public Cliente save(Cliente c) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally { em.close(); }
    }

    public Cliente findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try { return em.find(Cliente.class, id); } finally { em.close(); }
    }

    public List<Cliente> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return q.getResultList();
        } finally { em.close(); }
    }

    public Cliente update(Cliente c) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Cliente merged = em.merge(c);
            em.getTransaction().commit();
            return merged;
        } finally { em.close(); }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Cliente c = em.find(Cliente.class, id);
            if (c != null) em.remove(c);
            em.getTransaction().commit();
        } finally { em.close(); }
    }
}
