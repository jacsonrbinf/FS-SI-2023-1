package br.ufg.inf.application.data.entity;

import java.util.ArrayList;

public class Edificio {
	private String nome;
	private String endereco;
	private ArrayList<Apartamento> apartamentos = new ArrayList<Apartamento>();
	
	public Edificio(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}

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
	
	public void adicionarApartamento(Apartamento apartamento) {
		apartamentos.add(apartamento);
	}
	
	
}
