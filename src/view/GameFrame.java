/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author James
 */
public class GameFrame extends JFrame {
    private JPanel gameManagerPanel = new GameManager();
    
    public GameFrame(){
        initComponents();
        settingConfigFrame();
    }
    
    private void initComponents(){
        // Add Panel game vao frame
        // Hien thi Panel Game len Frame
        Container container = this.getContentPane();
        
        // Cho phep su kien se tuong tac tren thanh phan nay
        gameManagerPanel.setFocusable(true);
        
        container.add(gameManagerPanel);
    }
    
    // Chinh sua lai thong so cua frame nhu kich thuoc ...
    private void settingConfigFrame(){
        // Set location bang dau
        this.setLocation(200,200);
        // Tuy bien theo kich thuoc cac phan tu chua
        this.pack();
        // Cai dat khi nhan tat se tat chuong trinh (music background)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Hien thi man hinh
        this.setVisible(true);
    }
    
}
