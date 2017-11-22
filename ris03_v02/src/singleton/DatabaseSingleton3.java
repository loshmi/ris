/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

/**
 *
 * @author loshmi
 */
public class DatabaseSingleton3 extends DatabaseSingleton
{
    /**
     * Ovo je verzija koja je Thread sade
     * Ima tri komponente:
     * 1. Static variabla koja je na pocetku setovana na null
     * 2. Private konstruktor
     * 3. getInstance metoda koja proverava da li je instance promenljiva null, 
     * sto ukazuje na to da nije jos kreirana i koja je unutar synchronized da bi
     * samo jedna nit mogla da joj pristupi u jednom trenutku
     * 
     * Ovde dobijamo i lazy loading i thread safe
     */
    private DatabaseSingleton3 ()
    {
        
    }        

    private static class Holder
    {
       private static final DatabaseSingleton3 INSTANCE = new DatabaseSingleton3 ();
    }

    public static DatabaseSingleton3 getInstance()
    {
        return Holder.INSTANCE;
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
