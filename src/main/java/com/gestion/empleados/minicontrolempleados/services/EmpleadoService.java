/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.empleados.minicontrolempleados.services;

import com.gestion.empleados.minicontrolempleados.entity.Empleado;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author a_cle
 */
public interface EmpleadoService {
    
    public List<Empleado> findAll();
    
    public Page<Empleado> findAll(Pageable pageable);
    
    public void save(Empleado e);
    
    public Empleado findOne(Long id);
    
    public void delete(Long id);
}
