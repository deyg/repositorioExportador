package com.br.laboratorio.aplicacao.repositorio;

import java.util.ArrayList;
import java.util.List;

import com.br.laboratorio.aplicacao.entidade.Carro;

public class CarroRepositorio {
	
	private List<Carro> carros = null;
	
	
	public CarroRepositorio() {
		popularRepositorio();
	}
	
	public List<Carro> getCarros(){
		return carros;
	}
	
	private void popularRepositorio(){
		
		carros = new ArrayList<>();
		carros.add(new Carro("1", "Ford", "Branco", "2001"));
		carros.add(new Carro("2", "Mercedes", "Azul", "2002"));
		carros.add(new Carro("3", "BMW", "Preto", "2003"));
		carros.add(new Carro("4", "Volvo", "Marron", "2004"));
		carros.add(new Carro("5", "Fiat", "Amarelo", "2005"));
		carros.add(new Carro("6", "Honda", "Laranja", "2006"));
		carros.add(new Carro("7", "Ferrari", "Vermelho", "2007"));
		carros.add(new Carro("8", "Peugeot", "Lilas", "2008"));
		carros.add(new Carro("9", "Chevrolet", "branco", "2009"));
		carros.add(new Carro("10", "Renault", "branco", "2010"));
	}

}
