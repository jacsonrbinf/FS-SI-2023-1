package br.ufg.inf.application.data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufg.inf.application.data.entity.Apartamento;
import br.ufg.inf.application.data.entity.Edificio;



@Service
public class EdificioService {
private EdificioRepository edificioRepository;

	
	public EdificioService(EdificioRepository edificioRepository) {
		this.edificioRepository = edificioRepository;
		
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
   
}
