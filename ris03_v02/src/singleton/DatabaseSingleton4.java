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
public class DatabaseSingleton4 extends DatabaseSingleton
{
    /**
     * Ovo je verzija koja je Thread safe
     * Ima tri komponente:
     * 1. Static variabla koja je na pocetku inicijalizovana (kompajler stavlja ovu naredbu u konstruktor)
     * 2. Private konstruktor
     * 3. getInstance metoda koja proverava vraca instance
     * 
     * Static varijablama klase ne moze da se pristupi dok nisu potpuno inicijalizovane
     * Problem kod ove implementacije, je cinjenica da je objekat kreiran pre nego sto je potreban,
     * sto moze da bude problem ako je neki veliki objekat (Eager loading)
     */
    private static final DatabaseSingleton4 instance = new DatabaseSingleton4 ();
    
    private DatabaseSingleton4 ()
    {
        
    }
    
    public static DatabaseSingleton4 getInstance ()
    {        
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
