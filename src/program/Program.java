/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import program.utils.ConstrantStrings;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.rmi.runtime.Log;
import view.GameFrame;

/**
 *
 * @author James
 */
public class Program {

    
    public static void main(String[] args) {
        runMusicBackground();
        new GameFrame();
    }

    private static void runMusicBackground() {
        try{
            InputStream is = new FileInputStream(new File(ConstrantStrings.dirFileMusic)); // throws FileNotFoundException
            AudioStream as = new AudioStream(is);
            AudioPlayer.player.start(as);
        }
        catch(Exception e){ // 
            // Xu ly loi khong nhap duoc file
        }
    }
    
}
