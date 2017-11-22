/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loshmi
 */
public class Main
{
    public static void main(String[] args)
    {
        int noThreads = 10;
        int dbsOption = 2;
        int n = 100;
        ArrayList<NitZaKreiranje> niti = new ArrayList<>();
        
        for (int i = 0; i < noThreads; i++)
        {
            niti.add(new NitZaKreiranje (n, dbsOption));
        }

        for (NitZaKreiranje nit : niti)
        {
            nit.start();
            try {
                nit.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (NitZaKreiranje nit : niti)
        {
            try {
                nit.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (NitZaKreiranje nit : niti)
        {
            System.out.println(nit.getDbs());
        }
    }
}
