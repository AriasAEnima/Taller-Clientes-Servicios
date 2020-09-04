/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.tallerClientesServicios.microSpark;


import edu.escuelaing.arep.tallerClientesServicios.httpserver.Request;
import edu.escuelaing.arep.tallerClientesServicios.httpwriters.TextFileWriter;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;


/**
 *
 * @author J. Eduardo Arias
 */
public class MicroSpark{
    private static Map<String,FunctionResponse> webservices;
    private static DBConnection dbc;

    public MicroSpark() {
        webservices=new HashMap<>();
        dbc=new DBConnection();
        configuration();
    }
    
    
   
    public static void configuration(){
        getResponse("/hello",(req)->
             "<h1> Hola ! "+ req.getValFromQuery("name") +"</h1>"
        );
        getResponse("/users",(req)->{
                 Document doc=dbc.getUser(req.getValFromQuery("name"));                 
                return "<h1> Hola ! "+ doc.getString("name") 
                        +"</h1> <h2> Tu correo es : "+doc.getString("correo") 
                        + "</h2> <h2> y vives en : "+doc.getString("dir") +"</h2>";
            }            
        );
        getResponse("/potencia", (req) -> {
                int x = Integer.parseInt(req.getValFromQuery("x"));
                int y = Integer.parseInt(req.getValFromQuery("y"));
                return "<h1> El calculo de " + x + "^" + y + " da como resultado: " + Math.pow(x, y);
            }
        );   
        getResponse("/", (req) -> {
               return TextFileWriter.getStringFile("examples/index.html");
            }
        );      
                       
    }
    
    public static void getResponse(String query,FunctionResponse gf){
        webservices.put(query, gf);
    }
    
  
    
    public boolean existFunction(String key){
        return webservices.containsKey(key);
    }
    
    public String getResponse(String key,Request req){
        return webservices.get(key).response(req);
    }
      
    public interface FunctionResponse{
        public String response(Request req);            
    }

 
}
