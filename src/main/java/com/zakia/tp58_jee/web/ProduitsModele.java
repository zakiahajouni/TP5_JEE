package com.zakia.tp58_jee.web;

import entities.ProduitsEntity;

import java.util.ArrayList;
import java.util.List;

public class ProduitsModele {
    private String motCle;
    List<ProduitsEntity> produits = new ArrayList<>();

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<ProduitsEntity> getProduits() {
        return produits;
    }

    public void setProduits(List<ProduitsEntity> produits) {
        this.produits = produits;
    }}
