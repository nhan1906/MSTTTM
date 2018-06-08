/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import program.model.Board;
import program.model.Ghost;
import program.model.MapGame;
import program.thuattoan.ThuatToan;
import program.utils.Config;
import program.utils.ConstrantStrings;

/**
 *
 * @author James
 */
public class GameManager extends JPanel implements Runnable {

    // Các thành phần chứa trong game gồm có
    // Người chơi, các nhân vật, bản game, thuật toán tìm đường đi map với từng xe
    // Di chuyển ngẫu nhiên khi không có đích
    // Thread
    private Ghost ghostPlayer;
    private Board boardGame = new Board();
    
    // Map String voi ghost
    private Map<String, Ghost> mapGhost = new HashMap<>();
    // Map String voi Thuat Toan để dễ quản lý
    private Map<String, ThuatToan> mapThuatToan = new HashMap<>();
    
    private MapGame mapGame = new MapGame();
    //
    private Thread thread;
    
    public GameManager(){
        khoiTaoCacGiaTriBanDau();
        khoiTaoMapGhostVaThuatToan();
        
        
        // Chạy thuật toán A* cho 
        for(String key : mapThuatToan.keySet())
            mapThuatToan.get(key).chayThuatToan();
        //Cho phép điều khiển xe người chơi 
        controlEventPlayer();
        // Thread nay de chay AI
        thread = new Thread(this);
        thread.start();
    }
    
    private void controlEventPlayer(){
        // Ham duy chuyen co van de
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                
                if (key == KeyEvent.VK_LEFT && ghostPlayer.getFx() > 0 && !MapGame.isWallOrStreet(ghostPlayer.getFx(), ghostPlayer.getFy() - 1))
                    ghostPlayer.changePos(-1, 0);
                else if (key == KeyEvent.VK_RIGHT && ghostPlayer.getFx() < Config.nCell - 1 && !MapGame.isWallOrStreet(ghostPlayer.getFx(), ghostPlayer.getFy() + 1))
                    ghostPlayer.changePos(1, 0);
                else if (key == KeyEvent.VK_UP && ghostPlayer.getFy() > 0 && !MapGame.isWallOrStreet(ghostPlayer.getFy() -1 , ghostPlayer.getFx()))
                    ghostPlayer.changePos(0, -1);
                else if (key == KeyEvent.VK_DOWN && ghostPlayer.getFy() < Config.nCell -1  && !MapGame.isWallOrStreet(ghostPlayer.getFy() + 1 , ghostPlayer.getFx()))
                    ghostPlayer.changePos(0, 1);
                
                ghostPlayer.caculateRealPos();// Tính độ dài thực
                repaint();
            }
        }); 
    }
    
    private void khoiTaoCacGiaTriBanDau(){
        // Random ra loai hinh của map, loại hình của quân địch, loại hình của quân ta
        
        // Random ra map nếu có nhiều
        // Tương tác với file map và lấy ra các giá trị khởi tạo ban đầu
        // Lưu vào Biến Map
        mapGame.NhapMapTuFile(ConstrantStrings.dirFileMap);
        ghostPlayer = new Ghost(1, MapGame.fxPlayer, MapGame.fyPlayer, true, true);
    }
    private void khoiTaoMapGhostVaThuatToan(){
        mapGhost.put("Ghost0", new Ghost(2, MapGame.toaDoGhostBanDau[0][0], MapGame.toaDoGhostBanDau[0][1], false, true)); mapThuatToan.put("Ghost0", new ThuatToan(MapGame.toaDoGhostBanDau[0][0], MapGame.toaDoGhostBanDau[0][1]));
        mapGhost.put("Ghost1", new Ghost(2, MapGame.toaDoGhostBanDau[1][0], MapGame.toaDoGhostBanDau[1][1], false, true)); mapThuatToan.put("Ghost1", new ThuatToan(MapGame.toaDoGhostBanDau[1][0], MapGame.toaDoGhostBanDau[1][1]));
        mapGhost.put("Ghost2", new Ghost(2, MapGame.toaDoGhostBanDau[2][0], MapGame.toaDoGhostBanDau[2][1], false, false)); mapThuatToan.put("Ghost2", new ThuatToan(MapGame.toaDoGhostBanDau[2][0], MapGame.toaDoGhostBanDau[2][1]));
        mapGhost.put("Ghost3", new Ghost(2, MapGame.toaDoGhostBanDau[3][0], MapGame.toaDoGhostBanDau[3][1], false, false)); mapThuatToan.put("Ghost3", new ThuatToan(MapGame.toaDoGhostBanDau[3][0], MapGame.toaDoGhostBanDau[3][1]));
        mapGhost.put("Ghost4", new Ghost(2, MapGame.toaDoGhostBanDau[4][0], MapGame.toaDoGhostBanDau[4][1], false, false)); mapThuatToan.put("Ghost4", new ThuatToan(MapGame.toaDoGhostBanDau[4][0], MapGame.toaDoGhostBanDau[4][1]));
        mapGhost.put("Ghost5", new Ghost(2, MapGame.toaDoGhostBanDau[5][0], MapGame.toaDoGhostBanDau[5][1], false, false)); mapThuatToan.put("Ghost5", new ThuatToan(MapGame.toaDoGhostBanDau[5][0], MapGame.toaDoGhostBanDau[5][1]));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    
         boardGame.Draw(g);
         
         ghostPlayer.Draw(g);
         for (String key: mapGhost.keySet()){
             mapGhost.get(key).Draw(g);
         }
         
    } 
    @Override
    public void run() {
        
    }
    
    
    public Dimension getPreferredSize() {       
        return new Dimension(Config.nCell * Config.wCell + Config.add,Config.nCell *Config.hCell);
    }
}
