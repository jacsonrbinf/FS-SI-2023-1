package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Edificio {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	private String nome;
	private String endereco;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="edificio_id")
	private List<Apartamento> apartamentos;
	
	public Edificio() {
		
	}
	public Edificio(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public Edificio(int id, String nome, String endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public List<Apartamento> getApartamentos() {
		return apartamentos;
	}
	public void setApartamentos(List<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}

	public void adicionarApartamento(Apartamento apartamento) {
		apartamentos.add(apartamento);
	}
	
	public String mostraApartamentos() {
		String retorno = "Apartamentos do Edificio "+getNome()+": \n";
		for (int i=0; i<apartamentos.size();i++) {
			retorno += apartamentos.get(i).toString();
		}
		
		return retorno;
	}
	
	
}
