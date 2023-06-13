package br.ufg.inf.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.ufg.inf.application.models.Morador;


public interface MoradorRepository extends JpaRepository<Morador, Long>, JpaSpecificationExecutor<Morador>{

}
