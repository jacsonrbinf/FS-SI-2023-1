import java.util.List;

import javax.persistence.EntityManager;

import Dao.ApartamentoDao;
import Dao.EdificioDao;
import Dao.MoradorDao;
import Util.JPAUtil;
import models.Apartamento;
import models.Edificio;
import models.Morador;
import models.Situacao;



public class Teste {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		EntityManager en;
		en = JPAUtil.getEntityManager();
		
		/* Adicionar um novo morador
		MoradorDao moradorDao = new MoradorDao(en);
		Morador morador = new Morador("Jacson", "2222222", "62987654321");
		moradorDao.incluir(morador);
		*/
		
		/* Adicionar um novo Apartamento
    	MoradorDao moradorDao = new MoradorDao(en);
    	Morador morador = moradorDao.getMorador(1);
    	
    	Apartamento ap = new Apartamento(1005, 10, 250, Situacao.Quitado, morador);
    	ApartamentoDao apDao = new ApartamentoDao(en);
    	apDao.incluir(ap);    */
		
		
		
		/* Adicionar um novo edificio
		
    	EdificioDao edificioDao = new EdificioDao(en);
    	Edificio edificio = new Edificio("Condominio dos Buritis", "Alameda dos buritis");
    	edificioDao.incluir(edificio);*/
		 
		
		
		/* Adicionar apartamentos a um Edificio
    	ApartamentoDao apDao = new ApartamentoDao(en);
    	Apartamento ap = apDao.getApartamento(1);
    	
    	EdificioDao edificioDao = new EdificioDao(en);
    	Edificio edificio = edificioDao.getEdificio(1);
    	edificio.adicionarApartamento(ap);
    	edificioDao.alterarEdificio(edificio);*/
		
		/* Listar Apartamentos de um determinado Edificio*/
		EdificioDao edificioDao = new EdificioDao(en);
    	Edificio edificio = edificioDao.getEdificio(1);
    	
    	System.out.print(edificio.mostraApartamentos());
	}

}
