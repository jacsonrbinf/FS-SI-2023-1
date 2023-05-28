package Dao;

import java.util.List;

import javax.persistence.EntityManager;

import models.Morador;


public class MoradorDao {
	
	private EntityManager entityManager;
    public MoradorDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    public void incluir(Morador morador){
        entityManager.getTransaction().begin();
        entityManager.persist(morador);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public List<Morador> getAllMoradores(){
        entityManager.getTransaction().begin();
        List<Morador> moradores = entityManager.createQuery("select m from Morador as m").getResultList();
        entityManager.getTransaction().commit();
        
        return moradores;
    }
    
    public Morador getMorador(int id){
        entityManager.getTransaction().begin();
        Morador morador = entityManager.find(Morador.class,id);
        entityManager.getTransaction().commit();
        return morador;
    }
    
    public String deleteMorador(int id){
        
        
        String retorno = null;
        try {
        	Morador moradorDelete = entityManager.find(Morador.class,id);
            entityManager.getTransaction().begin();
            entityManager.remove(moradorDelete);
            entityManager.getTransaction().commit();
            retorno = moradorDelete.getNome();
          } catch(Exception ex) {
            entityManager.getTransaction().rollback();
          } finally {
            entityManager.close();
          }
        
        return retorno;
    }
    
    public Morador alterarMorador(Morador morador){
        entityManager.getTransaction().begin();
        Morador moradorAlterado = entityManager.merge(morador);
        entityManager.getTransaction().commit();
        return moradorAlterado;
    }  


}
