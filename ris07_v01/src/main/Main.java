/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import adapter.*;
import java.util.ArrayList;
import libraries.VLCPlayer;

/**
 *
 * @author loshmi
 */
public class Main
{
    public static void main (String [] args)
    {
        ArrayList<MediaPlayer> listPlayers = new ArrayList <> ();
        RISPlayer risPlayer = new RISPlayer ();
        VLCPlayer vlcPlayer = new VLCPlayer ();
        VLCPlayerAdapter vlcPlayerAdapter = new VLCPlayerAdapter(vlcPlayer);
        
        listPlayers.add(risPlayer);
        listPlayers.add(vlcPlayerAdapter);
        
        for (MediaPlayer mp : listPlayers)
        {
            mp.play();
        }
    }
}
