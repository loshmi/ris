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
public class DatabaseSingleton2 extends DatabaseSingleton
{
    /**Устаничка 64
     * Ovo je verzija koja je Thread safe
     * Ima tri komponente:
     * 1. Static variabla koja je na pocetku setovana na null
     * 2. Private konstruktor
     * 3. getInstance metoda koja proverava da li je instance promenljiva null, 
     * sto ukazuje na to da nije jos kreirana i koja je unutar synchronized da bi
     * samo jedna nit mogla da joj pristupi u jednom trenutku
     */
    private static DatabaseSingleton2 instance = null;
    
    private DatabaseSingleton2 ()
    {
        
    }
    
    public static synchronized DatabaseSingleton2 getInstance ()
    {
        if (instance == null)
        {
            // Pauza je dodata da bi se ilustrovalo kako ova je implementacija Thread safe
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DatabaseSingleton1.class.getName()).log(Level.SEVERE, null, ex);
            }
            instance = new DatabaseSingleton2 ();
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
