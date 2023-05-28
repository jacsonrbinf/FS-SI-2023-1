package Dao;

import java.util.List;

import javax.persistence.EntityManager;

import models.Apartamento;


public class ApartamentoDao {
	private EntityManager entityManager;
	
    public ApartamentoDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    public void incluir(Apartamento apartamento){
        entityManager.getTransaction().begin();
        entityManager.persist(apartamento);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public List<Apartamento> getAllApartamentos(){
        entityManager.getTransaction().begin();
        List<Apartamento> apartamentos = entityManager.createQuery("select m from Apartamento as m").getResultList();
        entityManager.getTransaction().commit();
        
        return apartamentos;
    }
    
    public Apartamento getApartamento(int id){
        entityManager.getTransaction().begin();
        Apartamento apartamento = entityManager.find(Apartamento.class,id);
        entityManager.getTransaction().commit();
        return apartamento;
    }
    
    public String deleteApartamento(int id){
        
        
        String retorno = null;
        try {
        	Apartamento apartamentoDelete = entityManager.find(Apartamento.class,id);
            entityManager.getTransaction().begin();
            entityManager.remove(apartamentoDelete);
            entityManager.getTransaction().commit();
            int andar = apartamentoDelete.getAndar();
            retorno = Integer.toString(andar);
          } catch(Exception ex) {
            entityManager.getTransaction().rollback();
          } finally {
            entityManager.close();
          }
        
        return retorno;
    }
    
    public Apartamento alterarEdificio(Apartamento apartamento){
        entityManager.getTransaction().begin();
        Apartamento apartamentoAlterado = entityManager.merge(apartamento);
        entityManager.getTransaction().commit();
        return apartamentoAlterado;
    }  
}
