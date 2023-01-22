/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.empleados.minicontrolempleados.util.reportes;

import com.gestion.empleados.minicontrolempleados.entity.Empleado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author a_cle
 */
public class EmpleadoExporterExcel {

    private XSSFWorkbook libro;
    private XSSFSheet hoja;

    private List<Empleado> listaEmpleados;

    public EmpleadoExporterExcel(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
        libro = new XSSFWorkbook();
        hoja = libro.createSheet("Empleados");
    }

    private void Cabecera() {
        Row fila = hoja.createRow(0);

        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);

        Cell celda = fila.createCell(0);
        celda.setCellValue("ID");
        celda.setCellStyle(estilo);

        celda = fila.createCell(1);
        celda.setCellValue("Nombre");
        celda.setCellStyle(estilo);

        celda = fila.createCell(2);
        celda.setCellValue("Apellido");
        celda.setCellStyle(estilo);

        celda = fila.createCell(3);
        celda.setCellValue("Mail");
        celda.setCellStyle(estilo);

        celda = fila.createCell(4);
        celda.setCellValue("Fecha");
        celda.setCellStyle(estilo);

        celda = fila.createCell(5);
        celda.setCellValue("Teléfono");
        celda.setCellStyle(estilo);

        celda = fila.createCell(6);
        celda.setCellValue("Sexo");
        celda.setCellStyle(estilo);

        celda = fila.createCell(7);
        celda.setCellValue("Salario");
        celda.setCellStyle(estilo);
    }

    private void datos() {
        int filas = 1;

        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);

        for (Empleado e : listaEmpleados) {
            Row fila = hoja.createRow(filas++);

            Cell celda = fila.createCell(0);
            celda.setCellValue(e.getId());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(1);
            celda.setCellValue(e.getNombre());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(2);
            celda.setCellValue(e.getApellido());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(3);
            celda.setCellValue(e.getMail());
            hoja.autoSizeColumn(3);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(4);
            celda.setCellValue(e.getFecha().toString());
            hoja.autoSizeColumn(4);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(5);
            celda.setCellValue(e.getTelefono());
            hoja.autoSizeColumn(5);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(6);
            celda.setCellValue(e.getSexo());
            hoja.autoSizeColumn(6);
            celda.setCellStyle(estilo);
            
            celda = fila.createCell(7);
            celda.setCellValue(e.getSalario());
            hoja.autoSizeColumn(7);
            celda.setCellStyle(estilo);
        }

    }
    
    public void exportar( HttpServletResponse response) throws IOException{
        Cabecera();
        datos();
        
        ServletOutputStream outputStream = response.getOutputStream();
        
        libro.write(outputStream);
        
        libro.close();
        outputStream.close();
        
    }

}
