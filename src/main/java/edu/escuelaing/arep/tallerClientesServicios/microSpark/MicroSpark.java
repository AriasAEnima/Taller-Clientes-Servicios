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
    
    /**
     * Un http server podra utilizar esta clasa para postear servicios.
     */
    public MicroSpark() {
        webservices=new HashMap<>();
        dbc=new DBConnection();
        configuration();
    }
    
    
    /**
     * Se puede añadir servicios usando el metodo getResponse el constructor llamara esta funcion
     */
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
    
    /**
     * Añadira una funcion de Respuesta al HashMap
     * @param path la ruta que se quiere colocar el servicio
     * @param gf la funcion de respuesta
     */
    public static void getResponse(String path,FunctionResponse gf){
        webservices.put(path, gf);
    }
    
  
    /**
     * Verifica si esiste una funcion relacionada con el path
     * @param key el path del posible servicio
     * @return si existe o no 
     */
    public boolean existFunction(String key){
        return webservices.containsKey(key);
    }
    
    
    /**
     * Ejecuta la respuesta con el Request relacionado (si se necesita)
     * @param key el path del servicio
     * @param req el request completo   
     * @return la ejecucion de la funcion de respuesta
     */
    public String getResponse(String key,Request req){
        return webservices.get(key).response(req);
    }
      
    /**
     * Interfaz que nos permite declarar funciones de respuesta tipo string y 
     * tener a mano un Request para utilizar elementos como el Query
     */
    public interface FunctionResponse{
        public String response(Request req);            
    }

 
}
