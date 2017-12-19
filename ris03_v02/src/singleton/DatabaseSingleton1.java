/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loshmi
 */
public class DatabaseSingleton1 extends DatabaseSingleton
{
    /**
     * Ovo je verzija koja nije Thread safe
     * Ima tri komponente:
     * 1. Static variabla koja je na pocetku setovana na null
     * 2. Private konstruktor
     * 3. getInstance metoda koja proverava da li je instance promenljiva null, 
     * sto ukazuje na to da nije jos kreirana
     */
    private static DatabaseSingleton1 instance = null;
    
    private DatabaseSingleton1 ()
    {
        
    }
    
    public static DatabaseSingleton1 getInstance ()
    {
        if (instance == null) 
        {
            // Pauza je dodata da bi se ilustrovalo kako ova implementacija nije Thread safe
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DatabaseSingleton1.class.getName()).log(Level.SEVERE, null, ex);
            }
            instance = new DatabaseSingleton1 ();
        }
        
        return instance;
    }
    
    public void connect()
    {
        System.out.println("Connected!");
    }
    
    public void disconnect()
    {
        System.out.println("Disonnected!");
    }
}
