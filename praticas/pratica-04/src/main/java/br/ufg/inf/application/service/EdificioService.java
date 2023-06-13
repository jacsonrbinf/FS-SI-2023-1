package br.ufg.inf.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufg.inf.application.models.Apartamento;
import br.ufg.inf.application.models.Edificio;
import br.ufg.inf.application.repositories.ApartamentoRepository;
import br.ufg.inf.application.repositories.EdificioRepository;



@Service
public class EdificioService {
	private EdificioRepository edificioRepository;
	private ApartamentoRepository apartamentoRepository;

	
	public EdificioService(EdificioRepository edificioRepository, ApartamentoRepository apartamentoRepository) {
		this.edificioRepository = edificioRepository;
		this.apartamentoRepository = apartamentoRepository;
		
	}
	
	public void deleteEdificio(Edificio edificio) {
		edificioRepository.delete(edificio);
    }

    public void saveEdificio(Edificio edificio) {
        if (edificio == null) { 
            System.err.println("Edificio está vazio");
            return;
        }
        edificioRepository.save(edificio);
    }

    public List<Edificio> findAllEdificios() {
    	
        return edificioRepository.findAll();
    }
    
    public List<Apartamento> findAllApartamentos() {
    	
        return apartamentoRepository.findAll();
    }
   
}
