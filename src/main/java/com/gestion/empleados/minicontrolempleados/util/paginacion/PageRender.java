/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.empleados.minicontrolempleados.util.paginacion;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author a_cle
 */
public class PageRender<T>{
    
    private String URL;
    private Page<T> page;
    private int total;
    private int actual;
    private int elementosPorPag;
    private List<PageItem> paginas;

    public PageRender(String URL, Page<T> page) {
        this.URL = URL;
        this.page = page;
        this.paginas = new ArrayList<PageItem>();
        
        elementosPorPag = 3;
        total = page.getTotalPages();
        actual = page.getNumber()+1;
        
        int desde, hasta;
        
        if (total<=elementosPorPag) {
            desde=1;
            hasta=total;
        } else {
            if (actual<=elementosPorPag/2) {
                desde=1;
                hasta=elementosPorPag;
            }else if(actual>=total-elementosPorPag/2){
                desde=total-elementosPorPag+1;
                hasta=elementosPorPag;
            }
            else{
                desde=actual-elementosPorPag/2;
                hasta=elementosPorPag;
            }   
            }
        
        for (int i = 0; i < hasta; i++) {
            paginas.add(new PageItem(desde+i, actual == desde +i));
        }
            
        }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public void setPaginas(List<PageItem> paginas) {
        this.paginas = paginas;
    }
    
    public boolean isLast(){
        return page.isLast();
    }
    
    public boolean isFirst(){
        return page.isFirst();
    }
    
    public boolean hasNext(){
        return page.hasNext();
    }
    
    public boolean hasPrevious(){
        return page.hasPrevious();
    }
        
        
    }
            
            
  
