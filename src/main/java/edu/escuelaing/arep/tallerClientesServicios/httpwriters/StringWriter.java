/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.tallerClientesServicios.httpwriters;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author J. Eduardo Arias
 */
public class StringWriter implements ResourceWriter {
        
    String response;
    public StringWriter(String response) {
        this.response = response;
    }     
     

    @Override
    public void write(String theuri, OutputStream out) {
        try (PrintWriter pr = new PrintWriter(out,true)) {            
            String header = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n";
            pr.println(header + response);
            pr.close();
        }
    }

    @Override
    public String exactType() {
        return "text/html";
    }
   
    
}
