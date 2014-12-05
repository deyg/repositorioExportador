package com.br.laboratorio.exportador;

import java.io.OutputStream;
import java.util.List;
import java.lang.reflect.*;
import java.util.ArrayList;

public class Exportador {

    private Class<?> classe;
    private List<?> objetos;
    List<String> metodosInvocadosSucesso;
    List<String> metodosInvocadosFalha;
    Planilha planilha;

    public Exportador() {
    }

    public List<String> getMetodosInvocadosFalha() {
        return metodosInvocadosFalha;
    }

    private void inicializar(List<?> objetos, OutputStream outputStream) {

        this.objetos = objetos;
        this.metodosInvocadosSucesso = new ArrayList<>();
        this.metodosInvocadosFalha = new ArrayList<>();
        this.planilha = new Planilha(outputStream);

        try {
            classe = objetos.get(0).getClass();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void exportar(List<?> objetos, Configuracao configuracao, OutputStream outputStream) {

        inicializar(objetos, outputStream);

        //titulo
        planilha.criarLinha();
        for (Campo campo : configuracao.getCampos()) {
            planilha.criarCelula(campo.getDescricao());
        }

        //corpo
        for (Object objeto : this.objetos) {
            planilha.criarLinha();
            for (Campo campo : configuracao.getCampos()) {
                Object resultado = getResultadoInvocacaoMetodo(objeto, campo.getMetodo());
                if (null != resultado) {
                    planilha.criarCelula(resultado);
                }
            }
        }

        planilha.escrever();

    }

    public void exportar(List<?> objetos, OutputStream outputStream) {

        inicializar(objetos, outputStream);

        Method[] metodos = null;

        try {
            metodos = classe.getMethods();
        } catch (Throwable e) {
            System.err.println(e);
        }

        comporTituloSemConfiguracao(metodos);
        comporCorpoSemConfiguracao(metodos);

        planilha.escrever();

    }
    
     private void comporTituloSemConfiguracao(Method[] metodos) {
        /*
         Invoca getResultadoInvocacaoMetodo sobre um item        
         da lista para carregar a lista metodosInvocadosSucesso
         para permitir montar o titulo da planilha
         */
        Object o = this.objetos.get(0);
        for (int i = 0; i < metodos.length; i++) {
            String metodo = metodos[i].getName();
            if (metodo.startsWith("get")) {
                getResultadoInvocacaoMetodo(o, metodo);
            }
        }
        //Compor Titulo
        planilha.criarLinha();
        for (String nomeColuna : metodosInvocadosSucesso) {
            planilha.criarCelula(nomeColuna.replaceFirst("get", ""));
        }
    }

    private void comporCorpoSemConfiguracao(Method[] metodos) {

        for (Object objeto : this.objetos) {
            planilha.criarLinha();
            for (int i = 0; i < metodos.length; i++) {
                String metodo = metodos[i].getName();
                if (metodo.startsWith("get")) {
                    Object resultado = getResultadoInvocacaoMetodo(objeto, metodo);
                    if (null != resultado) {
                        planilha.criarCelula(resultado);
                    }
                }
            }
        }
    }

    private void setMetodoInvocadoSucesso(String metodo) {
        if (!metodosInvocadosSucesso.contains(metodo)) {
            metodosInvocadosSucesso.add(metodo);
        }
    }

    private void setMetodoInvocadoFalha(String metodo) {
        if (!metodosInvocadosFalha.contains(metodo)) {
            metodosInvocadosFalha.add(metodo);
        }
    }

    private Object getResultadoInvocacaoMetodo(Object objeto, String nomeMetodo) {

        Object resultado = null;
        Method metodo = null;
        String tipoRetorno = new String();

        try {
            metodo = classe.getMethod(nomeMetodo);
            tipoRetorno = metodo.getReturnType().getSimpleName();

            if (tipoRetorno.equals("Date") || tipoRetorno.equals("Integer") || tipoRetorno.equals("String")) {
                resultado = metodo.invoke(objeto);
                setMetodoInvocadoSucesso(metodo.getName());
            } else {
                setMetodoInvocadoFalha(metodo.getName());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return resultado;
    }
}
