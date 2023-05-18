package br.ufg.inf.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Apartamento extends AbstractEntity{
	
	private int numero;
	private int andar;
	private double metragem;
	private String situacao;
	@OneToOne
	private Morador morador;
	
	

	
	public Morador getMorador() {
		return morador;
	}


	public void setMorador(Morador morador) {
		this.morador = morador;
	}


	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAndar() {
		return andar;
	}

	public void setAndar(int andar) {
		this.andar = andar;
	}

	public double getMetragem() {
		return metragem;
	}

	public void setMetragem(double metragem) {
		this.metragem = metragem;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}
