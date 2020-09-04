/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.tallerClientesServicios.httpwriters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J. Eduardo Arias
 */
public class TextFileWriter implements ResourceWriter{    
    private final String type;

    public TextFileWriter(String type) {
        this.type = type;
    }    
    
    /**
     * Escribe un archivo de texto utilizando el socket del cliente
     * @param file Debe ser el path del archivo relativo a raiz
     * @param outputStream     * 
     */
    @Override
    public void write(String file, OutputStream outputStream) {
        PrintWriter out;
        String outputLine;
        out = new PrintWriter(outputStream, true);
        outputLine = "HTTP/1.1 200 OK\r\n";
        outputLine+="Content-Type: text/"+type+"\r\n";
        outputLine+="\r\n\r\n";
        outputLine+=getStringFile(file);
        out.println(outputLine);
        out.close();         
    }     

    @Override
    public String exactType() {
        return "text/"+type;
    }
    
    public static String getStringFile(String pathfile){
        String outputLine = "";
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(pathfile));

            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                outputLine += bfRead;
            }
            return outputLine;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bf.close();
            } catch (IOException ex) {
                Logger.getLogger(TextFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return outputLine;
    }
    
}
