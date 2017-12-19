/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

/**
 *
 * @author loshmi
 */
public class RISPlayer implements MediaPlayer
{

    @Override
    public void play()
    {
        System.out.println("Play RIS media format");
    }
    
    
}
