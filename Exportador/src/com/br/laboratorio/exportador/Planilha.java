package com.br.laboratorio.exportador;

import java.io.OutputStream;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Planilha {

    private Integer numeroColuna;
    private Integer numeroLinha;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCell celula;
    private OutputStream outputStream;
    
    private Planilha() {}

    /**
     * 
     * @param outputStream originado de um
     * response.getOutputStream() 
     * ou de um FileOutputStream(nomeDoCaminhoDoArquivo);
     */
    public Planilha(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet();
        this.numeroColuna = 0;
        this.numeroLinha = 0;
    }
  
    public void escrever() {
        
        try {
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao exportar arquivo");
        }
    }

    public void criarLinha() {
        numeroColuna = 0;
        this.row = sheet.createRow(numeroLinha);
        numeroLinha++;
    }

    public void criarCelula(Object obj) {

        celula = row.createCell(numeroColuna);
        numeroColuna++;

        if (obj != null) {
            if (obj instanceof String) {
                celula.setCellValue((String) obj);
            }
            else if (obj instanceof Date) {
                celula.setCellValue((Date) obj);
                celula.setCellStyle(getEstiloData());
            }
             else if (obj instanceof Integer) {
                celula.setCellValue((Integer) obj);                
            }
        }
    }

    //Aplicar formato de data a celula
    private HSSFCellStyle getEstiloData() {
        HSSFCellStyle estiloData = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        estiloData.setDataFormat(format.getFormat("mm/dd/yyyy"));
        return estiloData;
    }
}
