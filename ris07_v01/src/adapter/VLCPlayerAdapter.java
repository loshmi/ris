/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import libraries.VLCPlayer;

/**
 *
 * @author loshmi
 */
public class VLCPlayerAdapter implements MediaPlayer
{
    private VLCPlayer vlcPlayer;

    public VLCPlayerAdapter(VLCPlayer vlcPlayer) {
        this.vlcPlayer = vlcPlayer;
    }
    
    

    @Override
    public void play()
    {
        vlcPlayer.playVideo();
    }
}
