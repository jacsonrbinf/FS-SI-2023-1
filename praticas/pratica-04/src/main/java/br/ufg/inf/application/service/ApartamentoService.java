package br.ufg.inf.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufg.inf.application.models.Apartamento;
import br.ufg.inf.application.models.Morador;
import br.ufg.inf.application.repositories.ApartamentoRepository;
import br.ufg.inf.application.repositories.MoradorRepository;

@Service
public class ApartamentoService {

	private ApartamentoRepository apartamentoRepository;
	private MoradorRepository moradorRepository;
	
	public ApartamentoService(ApartamentoRepository apartamentoRepository, MoradorRepository moradorRepository) {
		this.moradorRepository = moradorRepository;
		this.apartamentoRepository = apartamentoRepository;
	}
	
	public void deletApartamento(Apartamento apartamento) {
		apartamentoRepository.delete(apartamento);
    }

    public void saveApartamento(Apartamento apartamento) {
        if (apartamento == null) { 
            System.err.println("Morador está vazio");
            return;
        }
        apartamentoRepository.save(apartamento);
    }

    public List<Apartamento> findAllApartamentos() {
    	
        return apartamentoRepository.findAll();
    }
    
    public List<Morador> findAllMoradores() {
    	
        return moradorRepository.findAll();
    }

}
