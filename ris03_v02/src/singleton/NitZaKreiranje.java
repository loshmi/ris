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
public class NitZaKreiranje extends Thread
{
    private int n;
    private int dbsOption;
    private DatabaseSingleton dbs;

    public NitZaKreiranje() {
    }

    public NitZaKreiranje(int n, int dbsOption)
    {
        this.n = n;
        this.dbsOption = dbsOption;
    }

    public DatabaseSingleton getDbs() {
        return dbs;
    }
    
    public void run ()
    {
        for (int i = 0; i < n; i++)
        {
            dbs = getDataBaseSingleton();
        }
    }
    
    private DatabaseSingleton getDataBaseSingleton ()
    {
        switch (dbsOption)
        {
            case 1: return DatabaseSingleton1.getInstance();
            case 2: return DatabaseSingleton2.getInstance();
            case 3: return DatabaseSingleton3.getInstance();
            case 4: return DatabaseSingleton4.getInstance();
            default: return null;
        }
    }
}
