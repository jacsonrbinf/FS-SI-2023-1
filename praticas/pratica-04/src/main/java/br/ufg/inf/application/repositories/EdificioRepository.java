package br.ufg.inf.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.ufg.inf.application.models.Edificio;



public interface EdificioRepository extends JpaRepository<Edificio, Long>, JpaSpecificationExecutor<Edificio>{

}
