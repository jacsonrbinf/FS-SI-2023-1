package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Apartamento {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	private int numero;
	private int andar;
	private double metragem;
	private Situacao situacao;
	@OneToOne
	private Morador morador;

	public Apartamento() {
		
	}
	
	public Apartamento( int numero, int andar, double metragem, Situacao situacao, Morador morador) {
		this.numero = numero;
		this.andar = andar;
		this.metragem = metragem;
		this.situacao = situacao;
		this.morador = morador;
	}

	public Apartamento(int id, int numero, int andar, double metragem, Situacao situacao, Morador morador) {
		this.id = id;
		this.numero = numero;
		this.andar = andar;
		this.metragem = metragem;
		this.situacao = situacao;
		this.morador = morador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}
	
	public String toString() {
		return "\nApatamento:" +
				"\nNúmero: " + getNumero() + 
				"\nAndar: " + getAndar() + 
				"\nMetragem: " + getMetragem() + 
				"\nSituação: " + getSituacao()+
				 getMorador().toString()+
				"\n----------\n";
	}
	
}
