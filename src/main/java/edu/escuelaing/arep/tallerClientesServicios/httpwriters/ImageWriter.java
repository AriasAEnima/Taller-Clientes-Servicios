/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.tallerClientesServicios.httpwriters;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 *
 * @author J. Eduardo Arias
 */
public class ImageWriter implements ResourceWriter{
    private final String type;
    
    public ImageWriter(String type) {
        this.type = type;
    }    
    
    /**
     * Escribe bits de una imagen utilizando el socket del cliente.
     * @param file path del archivo
     * @param outputStream     * 
     */
    @Override
    public void write(String file,OutputStream outputStream) {        
        FileInputStream inputImage;
        try {
            File graphicResource= new File(file);
            inputImage = new FileInputStream(graphicResource);
            byte[] bytes = new byte[(int) graphicResource.length()];
            inputImage.read(bytes);
            DataOutputStream binaryOut;
            binaryOut = new DataOutputStream(outputStream);
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/"+type+"\r\n");
            binaryOut.writeBytes("Content-Length: " + bytes.length);
            binaryOut.writeBytes("\r\n\r\n");
            binaryOut.write(bytes);
            binaryOut.close();
        } catch (IOException ex) {
            new ErrorWriter("404 NOT FOUND").write("", outputStream);
        }      
    }

    @Override
    public String exactType() {
        return "image/"+type;
    }

   
    
}
