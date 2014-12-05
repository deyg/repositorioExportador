/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.laboratorio.exportador;

import java.util.ArrayList;
import java.util.List;


public class Configuracao {
    
    private List<Campo> campos = new ArrayList<>();
    
    public Configuracao(){
        campos = new ArrayList<>();        
    }
        
    
    public int getQtdeCampos(){
        return campos.size();
    }
    
    public void adicionarCampo(String metodo, String descricao){
        campos.add(new Campo(metodo, descricao));
    }

    /**
     * @return the campos
     */
    public List<Campo> getCampos() {
        return campos;
    }

    /**
     * @param campos the campos to set
     */
    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }    
    
}
