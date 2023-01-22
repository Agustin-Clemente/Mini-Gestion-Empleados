/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.empleados.minicontrolempleados;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author a_cle
 */
public class PasswordGenerator {
    
    public static void main(String[] args) {
        
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        String password= "12345";
        String encodedPassword= encoder.encode(password);
        
        System.out.println(encodedPassword);
    }
    
}
