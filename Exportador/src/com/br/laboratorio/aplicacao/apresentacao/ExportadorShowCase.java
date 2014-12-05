package com.br.laboratorio.aplicacao.apresentacao;

import com.br.laboratorio.aplicacao.entidade.Carro;
import com.br.laboratorio.aplicacao.entidade.Pessoa;
import com.br.laboratorio.aplicacao.repositorio.CarroRepositorio;
import com.br.laboratorio.aplicacao.repositorio.PessoaRepositorio;
import com.br.laboratorio.exportador.Configuracao;
import com.br.laboratorio.exportador.Exportador;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExportadorShowCase {

    private FileOutputStream arquivo = null;
    private final String separador = System.getProperty("file.separator");
    //private final String pasta = "/tmp";
    private final String pasta = "C:/..zeus/laboratorio/Exportador";
    private final String caminho1 = pasta + "/arquivo1.xls".replace("/", separador);
    private final String caminho2 = pasta + "/arquivo2.xls".replace("/", separador);
    private final String caminho3 = pasta + "/arquivo3.xls".replace("/", separador);

    public ExportadorShowCase() {
    }

    public void apresentar() {

        System.out.println("Prova de conceito - Planilhas gravadas na pasta: " + pasta + "\n");                
        exportarPessoasTodosCampos();
        exportaCarros();
        exportarPessoas();  
    }
    
    private void exportaCarros() {

        CarroRepositorio rep = new CarroRepositorio();
        List<Carro> carros = rep.getCarros();

        Configuracao configuracao = new Configuracao();
        configuracao.adicionarCampo("getAno", "Ano");
        configuracao.adicionarCampo("getMarca", "Marca");
        configuracao.adicionarCampo("getCor", "Cor");

        Exportador exportador = new Exportador();

        try {
            arquivo = new FileOutputStream(caminho1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportadorShowCase.class.getName()).log(Level.SEVERE, null, ex);
        }

        exportador.exportar(carros, configuracao, arquivo);

    }

    private void exportarPessoas() {

        PessoaRepositorio rep = new PessoaRepositorio();
        List<Pessoa> pessoas = rep.getPessoas();

        Configuracao configuracao = new Configuracao();
        configuracao.adicionarCampo("getAnoNascimento", "Ano de Nascimento");
        configuracao.adicionarCampo("getNome", "Nome");

        Exportador exportador = new Exportador();

        try {
            arquivo = new FileOutputStream(caminho2);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportadorShowCase.class.getName()).log(Level.SEVERE, null, ex);
        }

        exportador.exportar(pessoas, configuracao, arquivo);

    }

    private void exportarPessoasTodosCampos() {

        Exportador exportadorPessoasTodosCampos = new Exportador();

        PessoaRepositorio rep = new PessoaRepositorio();
        List<Pessoa> pessoas = rep.getPessoas();

        try {
            arquivo = new FileOutputStream(caminho3);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportadorShowCase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            exportadorPessoasTodosCampos.exportar(pessoas, arquivo);
        } catch (Exception e) {
            System.err.println("Exportacao falhou: " + e.getMessage());
        }

        System.err.println("Falha ao invocar os metodos:");
        for (String erro : exportadorPessoasTodosCampos.getMetodosInvocadosFalha()) {
            System.err.println(erro);
        }

    }

    

    /*
     private HttpServletResponse getResponseParaSaidaArquivo(nomeArquivo){     
    
     //nomeArquivo = "arquivo01.xls";
    
     HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
     response.setContentType("application/vnd.ms-excel");
     response.setHeader("Content-Disposition", "attachment;filename="+nomeArquivo);
     response.setHeader("Pragma", "no-cache");
    
     return response;
     }
     */
}
