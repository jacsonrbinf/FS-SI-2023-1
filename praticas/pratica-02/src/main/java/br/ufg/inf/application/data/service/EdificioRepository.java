package br.ufg.inf.application.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.ufg.inf.application.data.entity.Edificio;



public interface EdificioRepository extends JpaRepository<Edificio, Long>, JpaSpecificationExecutor<Edificio>{

}
