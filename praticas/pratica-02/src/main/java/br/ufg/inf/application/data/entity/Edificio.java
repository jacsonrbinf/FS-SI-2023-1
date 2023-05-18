package br.ufg.inf.application.data.entity;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Edificio extends AbstractEntity{
	private String nome;
	private String endereco;

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

	
	
	
	
	
	
}
