package br.ufg.inf.application.data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufg.inf.application.data.entity.Morador;

@Service
public class MoradorService {

	private MoradorRepository moradorRepository;
	
	public MoradorService(MoradorRepository moradorRepository) {
		this.moradorRepository = moradorRepository;
	}
	
	public void deleteMorador(Morador morador) {
		moradorRepository.delete(morador);
    }

    public void saveMorador(Morador morador) {
        if (morador == null) { 
            System.err.println("Morador está vazio");
            return;
        }
        moradorRepository.save(morador);
    }

    public List<Morador> findAllMoradores() {
    	
        return moradorRepository.findAll();
    }
}
