package com.br.laboratorio.aplicacao.repositorio;

import java.util.ArrayList;
import java.util.List;

import com.br.laboratorio.aplicacao.entidade.Pessoa;

public class PessoaRepositorio {
	
	private List<Pessoa> pessoas = null;
	
	public PessoaRepositorio(){
		popularRepositorio();
	}
	
	public List<Pessoa> getPessoas(){
		return pessoas;
	}
	
	private void popularRepositorio(){
		
		pessoas = new ArrayList<>();
		pessoas.add(new Pessoa("1", "João", 1981));
		pessoas.add(new Pessoa("2", "José", 1982));
		pessoas.add(new Pessoa("3", "Adão", 1983));
		pessoas.add(new Pessoa("4", "Cauã", 1984));
		pessoas.add(new Pessoa("5", "Caio", 1985));		
				
	}
	
}
