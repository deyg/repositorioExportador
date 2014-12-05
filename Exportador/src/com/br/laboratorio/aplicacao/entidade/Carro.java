package com.br.laboratorio.aplicacao.entidade;

public class Carro {
	
	private String id;
	private String marca;
	private String cor;
	private String ano;
	
	public Carro(){}
	
	
	public Carro(String id, String marca, String cor, String ano) {
		super();
		this.id = id;
		this.marca = marca;
		this.cor = cor;
		this.ano = ano;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	

}
