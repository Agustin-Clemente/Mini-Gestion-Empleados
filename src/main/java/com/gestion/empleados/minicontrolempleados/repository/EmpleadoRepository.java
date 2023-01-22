/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.empleados.minicontrolempleados.repository;

import com.gestion.empleados.minicontrolempleados.entity.Empleado;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author a_cle
 */
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long>{
    
}
