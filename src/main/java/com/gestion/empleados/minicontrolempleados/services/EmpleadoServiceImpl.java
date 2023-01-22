/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.empleados.minicontrolempleados.services;

import com.gestion.empleados.minicontrolempleados.entity.Empleado;
import com.gestion.empleados.minicontrolempleados.repository.EmpleadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author a_cle
 */
@Service
public class EmpleadoServiceImpl implements EmpleadoService{
    
    @Autowired
    private EmpleadoRepository empleadoRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoRepo.findAll();
                }

    @Override
    @Transactional(readOnly = true)
    public Page<Empleado> findAll(Pageable pageable) {
        return empleadoRepo.findAll(pageable);
                }

    @Override
    @Transactional
    public void save(Empleado e) {
       empleadoRepo.save(e);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findOne(Long id) {
        return empleadoRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        empleadoRepo.deleteById(id);
    }
    
}
