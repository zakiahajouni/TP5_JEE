package com.zakia.tp58_jee.web;

import com.zakia.tp58_jee.dao.ProduitDao;
import com.zakia.tp58_jee.dao.ProduitDaoImpl;
import entities.ProduitsEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "produitsServlet", urlPatterns = {"/produits", "*.do"})
public class ProduitsServlet extends HttpServlet {
    ProduitDao metier;
    @Override
    public void init() throws ServletException {
        metier = new ProduitDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path=request.getServletPath();
        if (path.equals("/index.do"))
        {
            request.getRequestDispatcher("produits.jsp").forward(request,response);
        }
        else if (path.equals("/chercher.do"))
        {
            String motCle=request.getParameter("motCle");
            ProduitsModele model= new ProduitsModele();
            model.setMotCle(motCle);
            List<ProduitsEntity> prods = metier.produitsParMC(motCle);
            model.setProduits(prods);
            request.setAttribute("model", model);

            request.getRequestDispatcher("produits.jsp").forward(request,response);
        }
        else if (path.equals("/saisie.do")) {
            request.getRequestDispatcher("saisieProduit.jsp").forward(request,response);
        }
        else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            ProduitsEntity p = metier.save(new ProduitsEntity(nom, prix));
            request.setAttribute("produit", p);
            request.getRequestDispatcher("confirmation.jsp").forward(request,response);
        }
        else if (path.equals("/supprimer.do")) {
            int idProduit = Integer.parseInt(request.getParameter("id"));
            metier.deleteProduit(idProduit);
            response.sendRedirect("chercher.do?motCle=");
        } else if (path.equals("/editer.do")) {
            int idProduit = Integer.parseInt(request.getParameter("id"));
            ProduitsEntity p = metier.getProduit(idProduit);
            request.setAttribute("produit", p);
            request.getRequestDispatcher("editerProduit.jsp").forward(request,response);
        } else if (path.equals("/update.do") && request.getMethod().equals("POST")) {
            int idProduit = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            ProduitsEntity p = new ProduitsEntity();
            p.setIdProduit(idProduit);
            p.setNomProduit(nom);
            p.setPrix(prix);
            metier.updateProduit(p);
            request.setAttribute("produit", p);
            request.getRequestDispatcher("confirmation.jsp").forward(request,response);
        }
        else {
            response.sendError(response.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}