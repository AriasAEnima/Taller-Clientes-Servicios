/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.tallerClientesServicios.microSpark;

import edu.escuelaing.arep.tallerClientesServicios.httpserver.ServerHttp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J. Eduardo Arias
 */
public class MicroSparkServer {
    public static void main(String[] args) {
        try {
            MicroSpark mp=new MicroSpark();
            ServerHttp server=new ServerHttp(mp);
            server.start();
            
        } catch (Exception ex) {
            Logger.getLogger(MicroSparkServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }
    
}
