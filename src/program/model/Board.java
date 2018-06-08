/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.model;

import java.awt.Color;
import java.awt.Graphics;
import program.utils.Config;

/**
 *
 * @author James
 */
public class Board extends GameObject{

    private int mapPoints[][];
    
    @Override
    public void Draw(Graphics g) {
        // Fill background
        mapPoints = MapGame.map;
        g.setColor(Color.black);
        g.fillRect(0, 0, Config.wCell * (Config.nCell + 1), Config.hCell* ( Config.nCell + 1));
        for (int i = 0; i < Config.nCell; i++)
            for (int j = 0; j < Config.nCell; j++){
                int x = j * Config.wCell; // Vị trí của CELL
                int y = i * Config.hCell; // Vị trí của CELL
                
                if( mapPoints[i][j] == 1){
                    g.setColor(new Color(33,33,222));
                    g.fillRect(x + 5, y + 5, 3 , 3);
                }
                else if( mapPoints[i][j] != 1){
                    //Vẽ background đường đi ok r
                    g.setColor(new Color(255, 184, 151));
                    g.fillRect(x + 5, y + 5, 2, 2 );
                }
            }
    }
    
}
