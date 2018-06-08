/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import program.utils.Config;

/**
 *
 * @author James
 */
public class Ghost extends GameObject implements MoveRandom{

    // Vi tri o thu may bang board
    private int fx, fy;
    // Vị trí thực khoảng cách trên bảng
    private int realFx, realFy;
    // Là người chơi hay không
    private boolean isPlayer;
    // Là phe ta hay phe địch true là phe ta, false là phe địch
    private boolean isMine;
    // Hướng ngẫu nhiên di chuyển khi không có vật phẩm
    private int direction;
    // Khoảng di chuyển;
    private int distance;
    // Sử dụng hình ảnh hay vẽ ra nhân vật
//    private BufferedImage player;
    public Ghost(int direction, int fx, int fy, boolean isPlayer, boolean isMine){
        this.direction = direction;
        this.fx = fx;
        this.fy = fy;
        this.isPlayer = isPlayer;
        this.isMine = isMine;
        
        caculateRealPos();
        
    }
    
    public void caculateRealPos(){
        realFx = fx * Config.wCell;
        realFy = fy * Config.hCell;
    }
    @Override
    public int findDirectionNextMove(int fx, int fy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int distanceCanMove(int fx, int fy, int dir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Draw(Graphics g) {
        if(isPlayer){
            g.setColor(Color.red);
            g.fillOval(fx * Config.wCell, fy * Config.hCell, 5, 5);
       }    
        else if(isMine == false){                           // Nếu là đội địch
           g.setColor(Color.green);
           g.fillOval(fx * Config.wCell, fy * Config.hCell, 5, 5);
        } 
        else {
            g.setColor(Color.white);
            g.fillOval(fx * Config.wCell, fy * Config.hCell, 5, 5);
        }
       if(Target.isExist){  
           g.setColor(Color.yellow);
            g.fillOval(Target.fxTarget * Config.wCell, Target.fyTarget * Config.hCell, 10, 10);
       }
    }
    
    
    public int getFx(){
        return fx;
    }
    
    public int getFy(){
        return fy;
    }
    
    public void changePos(int addX, int addY){
        fx += addX;
        fy += addY;
    }
    
    
}
