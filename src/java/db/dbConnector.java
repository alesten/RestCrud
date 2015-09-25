package db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class dbConnector {
    private static dbConnector instance;
    
    private EntityManagerFactory emf;
    
    public static dbConnector Instance(){
        if(instance == null)
            instance = new dbConnector();
        return instance;
    }
    
    private dbConnector(){
        emf = Persistence.createEntityManagerFactory("RestCrudPU");
    }
    
    public EntityManager getEm(){
        return emf.createEntityManager();
    }
}
