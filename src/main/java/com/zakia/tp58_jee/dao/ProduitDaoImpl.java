package com.zakia.tp58_jee.dao;

import com.zakia.tp58_jee.utils.JpaUtil;
import entities.ProduitsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ProduitDaoImpl implements ProduitDao{
    private EntityManager entityManager = JpaUtil.getEntityManager("default");

    @Override
    public ProduitsEntity save(ProduitsEntity produits) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(produits);
        entityTransaction.commit();
        return produits;
    }

    @Override
    public List<ProduitsEntity> produitsParMC(String mc) {
        List<ProduitsEntity> prods = entityManager.createQuery("select p from ProduitsEntity p where p.nomProduit like :x")
                .setParameter("x", "%" + mc + "%")
                .getResultList();
        return prods;
    }

    @Override
    public ProduitsEntity getProduit(int idProduit) {
        return entityManager.find(ProduitsEntity.class, idProduit);
    }

    @Override
    public ProduitsEntity updateProduit(ProduitsEntity produits) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(produits);
        entityTransaction.commit();
        return produits;
    }

    @Override
    public void deleteProduit(int idProduit) {
        ProduitsEntity produits = entityManager.find(ProduitsEntity.class, idProduit);
        entityManager.getTransaction().begin();
        entityManager.remove(produits);
        entityManager.getTransaction().commit();
    }
}

