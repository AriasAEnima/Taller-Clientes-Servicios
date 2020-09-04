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
public class ErrorWriter implements ResourceWriter{ 
  
    private final String message;
   
    /**
     * @param message Codigo+info
     */
    public ErrorWriter(String message ){        
        this.message=message;
    }
    
   /**
    * Escribe un HTML con el mensaje del contructor al socket del cliente
    * @param file Es ignorando este parametro en esta implementacion.
    * @param outputStream donde se pretende responder.
    */
    @Override
    public void write(String file, OutputStream outputStream) {
        try (PrintWriter out = new PrintWriter(outputStream, true)) {
            String outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>"+message+"</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Error "+message+ "</h1>"
                    + "</body>"
                    + "</html>";
            out.println(outputLine);
            out.close();
        } 
        
    }

    @Override
    public String exactType() {
        return "error";
    }
    
}
