package br.ufg.inf.application.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Edificio extends AbstractEntity{
	private String nome;
	private String endereco;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="edificio_id")
	private List<Apartamento> apartamentos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Apartamento> mostrarApartamentos() {
		return apartamentos;
	}
	public void setApartamentos(List<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}

	public void adicionarApartamento(Apartamento apartamento) {
		apartamentos.add(apartamento);
	}
	
	
	
	
	
}
