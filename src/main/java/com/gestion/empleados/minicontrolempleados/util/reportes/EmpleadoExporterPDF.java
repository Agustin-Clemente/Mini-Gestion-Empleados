/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.empleados.minicontrolempleados.util.reportes;

import com.gestion.empleados.minicontrolempleados.entity.Empleado;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import java.util.List;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a_cle
 */
public class EmpleadoExporterPDF {
    
    private List<Empleado> listaEmpleados;

    public EmpleadoExporterPDF() {
    }

    public EmpleadoExporterPDF(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    private void cabeceraDeTabla(PdfPTable tabla){
        PdfPCell celda= new PdfPCell();
        
        celda.setBackgroundColor(Color.RED);
        celda.setPadding(5);
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);
        
        celda.setPhrase(new Phrase("ID",fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Nombre",fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Apellido",fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Mail",fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Fecha",fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Teléfono",fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Sexo",fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Salario",fuente));
        tabla.addCell(celda);
    }
    
    private void datosTabla(PdfPTable tabla){
        for(Empleado e:listaEmpleados){
            tabla.addCell(String.valueOf(e.getId()));
            tabla.addCell(e.getNombre());
            tabla.addCell(e.getApellido());
            tabla.addCell(e.getMail());
            tabla.addCell(e.getFecha().toString());
            tabla.addCell(String.valueOf(e.getTelefono()));
            tabla.addCell(e.getSexo());
            tabla.addCell(String.valueOf(e.getSalario()));
        }
    }
    
    public void exportar(HttpServletResponse response) throws IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        
        doc.open();
        
        Font fuente= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);
        
        Paragraph titulo = new Paragraph("Lista de empleados", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(titulo);
        
        PdfPTable tabla= new PdfPTable(8);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{1f,2.3f,2.3f,6f,2.9f,3.5f,2.f,2.2f});
        tabla.setWidthPercentage(110);
        
        cabeceraDeTabla(tabla);
        datosTabla(tabla);
        
        doc.add(tabla);
        doc.close();
        
        
    }
    
}
