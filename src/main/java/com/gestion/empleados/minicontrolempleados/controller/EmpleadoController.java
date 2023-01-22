/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.empleados.minicontrolempleados.controller;

import com.gestion.empleados.minicontrolempleados.entity.Empleado;
import com.gestion.empleados.minicontrolempleados.services.EmpleadoService;
import com.gestion.empleados.minicontrolempleados.util.paginacion.PageRender;
import com.gestion.empleados.minicontrolempleados.util.reportes.EmpleadoExporterExcel;
import com.gestion.empleados.minicontrolempleados.util.reportes.EmpleadoExporterPDF;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 *
 * @author a_cle
 */
//@RestController
@Controller 
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService empleadoService;
    
    @GetMapping({"/","/listar",""})
    public String traerEmpleados(@RequestParam(name="page",defaultValue = "0") int page, Model modelo){
            Pageable pageRequest= PageRequest.of(page,5);
            Page<Empleado> empleados = empleadoService.findAll(pageRequest);
            PageRender<Empleado> pageRender= new PageRender<>("/listar", empleados);
            
            modelo.addAttribute("titulo", "Lista de empleados");
            modelo.addAttribute("empleados", empleados);
            modelo.addAttribute("page", pageRender);
            
            return "listar";
    }
    
    @GetMapping("/ver/{id}")
    public String verDetalles(@PathVariable(value = "id") Long id, Map<String,Object> modelo, RedirectAttributes flash){
        Empleado e = empleadoService.findOne(id);
        if(e == null){
            flash.addFlashAttribute("error", "El empleado no existe");
            return "redirect:/listar";
        }
        
        modelo.put("empleado", e);
        modelo.put("titulo","Detalles del empleado " + e.getNombre());
        return "ver";
    }
        
    @GetMapping("/formulario")
    public String agregarEmpleado(Map<String,Object>modelo){
        Empleado e= new Empleado();
        modelo.put("empleado", e);
        modelo.put("titulo", "Agregar empleado");
        return "formulario";
    }
    
    @PostMapping("/formulario")
    public String guardarEmpleado(@Valid Empleado e, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status){
        if(result.hasErrors()){
            modelo.addAttribute("titulo", "Registrar empleado");
            return "formulario";
        }
        
        String mensaje = (e.getId() !=null) ? "Empleado editado con éxito" : "Empleado agregado";
        
        empleadoService.save(e);
        status.setComplete();
        flash.addFlashAttribute("succes", mensaje);
        return "redirect:/listar"; 
    }
    
    @GetMapping("/formulario/{id}")
    public String editarEmpleado(@PathVariable(value = "id") Long id, Map<String,Object> modelo, RedirectAttributes flash){
        Empleado e= null;
        if(id > 0) {
            e= empleadoService.findOne(id);
            if(e == null) {
                flash.addFlashAttribute("error", "El empleado no existe");
                return "redirect:/listar";
            }
        }
        else{
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/listar";
        }
        
        modelo.put("empleado", e);
        modelo.put("titulo", "Editar empleado");
        return "formulario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable(value = "id") Long id, RedirectAttributes flash ){
        if (id>0){
            empleadoService.delete(id);
            flash.addFlashAttribute("success", "Empleado eliminado");
        }
        return "redirect:/listar";
    }
    
    @GetMapping("/pdf")
    public void exportarPDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        
        DateFormat dateFormatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual= dateFormatter.format(new Date());
        
        String cabecera= "Content-Disposition";
        String valor= "attachment; filename=Empleados_" + fechaActual + ".pdf";
        
        response.setHeader(cabecera, valor);
        
        List<Empleado> empleados= empleadoService.findAll();
        
        EmpleadoExporterPDF exporter= new EmpleadoExporterPDF(empleados);
        
        exporter.exportar(response);
    }
    
    @GetMapping("/excel")
    public void exportarExcel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        
        DateFormat dateFormatter= new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual= dateFormatter.format(new Date());
        
        String cabecera= "Content-Disposition";
        String valor= "attachment; filename=Empleados_" + fechaActual + ".xlsx";
        
        response.setHeader(cabecera, valor);
        
        List<Empleado> empleados= empleadoService.findAll();
        
        EmpleadoExporterExcel exporter= new EmpleadoExporterExcel(empleados);
        
        exporter.exportar(response);
    }
    
}
