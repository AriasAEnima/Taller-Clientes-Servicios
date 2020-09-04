/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.tallerClientesServicios.microSpark;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author J. Eduardo Arias
 */
public class DBConnection {
    private MongoCollection users;

    public DBConnection() {
           MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://admin:admin@testquery.h2f1e.mongodb.net/TestQuery?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("Test");  
        users = database.getCollection("users");
        
    }
    
    /**
     * Devuelve un User de la base de datos segun el nombre
     * @param name el nombre del usuario
     * @return Devuelve el usuario de la base de datos
     */
    public Document getUser(String name){
        return  (Document) users.find(new Document("name",name)).first();
    }
    
//    
//    public static void main(String[] args){
//         MongoClientURI uri = new MongoClientURI(
//            "mongodb+srv://admin:admin@testquery.h2f1e.mongodb.net/TestQuery?retryWrites=true&w=majority");
//
//        MongoClient mongoClient = new MongoClient(uri);
//        MongoDatabase database = mongoClient.getDatabase("Test");  
//        MongoCollection users = database.getCollection("users");
//        Document document=new Document("name","marco");
//        document.append("dir", "Toberin");
//         document.append("correo", "maria@gmail.com");
//         users.insertOne(document);
//               Document u=(Document) users.find(new Document("name","maria")).first();
//        String a=u.getString("name");
//        
//        
//    }
//    
    
    
   
     
}
