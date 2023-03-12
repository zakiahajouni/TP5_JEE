package com.zakia.tp58_jee.dao;

import entities.ProduitsEntity;

import java.util.List;

public interface ProduitDao {
    public ProduitsEntity save(ProduitsEntity produits);
    public List<ProduitsEntity> produitsParMC(String mc);
    public ProduitsEntity getProduit(int idProduit);
    public ProduitsEntity updateProduit(ProduitsEntity produits);
    public void deleteProduit(int idProduit);
}
