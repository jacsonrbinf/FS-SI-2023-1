package Dao;

import java.util.List;

import javax.persistence.EntityManager;

import models.Edificio;



public class EdificioDao {
private EntityManager entityManager;
	
    public EdificioDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    public void incluir(Edificio edificio){
        entityManager.getTransaction().begin();
        entityManager.persist(edificio);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public List<Edificio> getAllEdificios(){
        entityManager.getTransaction().begin();
        List<Edificio> edificios = entityManager.createQuery("select m from Edificio as m").getResultList();
        entityManager.getTransaction().commit();
        
        return edificios;
    }
    
    public Edificio getEdificio(int id){
        entityManager.getTransaction().begin();
        Edificio edificio = entityManager.find(Edificio.class,id);
        entityManager.getTransaction().commit();
        return edificio;
    }
    
    public String deleteEdificio(int id){
        
        
        String retorno = null;
        try {
        	Edificio edificioDelete = entityManager.find(Edificio.class,id);
            entityManager.getTransaction().begin();
            entityManager.remove(edificioDelete);
            entityManager.getTransaction().commit();
            retorno =edificioDelete.getNome();
          } catch(Exception ex) {
            entityManager.getTransaction().rollback();
          } finally {
            entityManager.close();
          }
        
        return retorno;
    }
    
    public Edificio alterarEdificio(Edificio edificio){
        entityManager.getTransaction().begin();
        Edificio edificioAlterado = entityManager.merge(edificio);
        entityManager.getTransaction().commit();
        return edificioAlterado;
    }  
}
