/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.model;

import java.io.File;
import java.util.Scanner;
import program.controller.GiaoTiepFile;
import program.utils.Config;

/**
 *
 * @author James
 */
public class MapGame implements GiaoTiepFile{
    // Cấu trúc file Map
    /*
    * Lưu trữ tọa độ người chơi ban đầu
    * Lưu trữ tạo độ đích
    * Lưu trữ số lượng quân
    * Lưu trữ mảng vị trí xe
    * Lưu trữ map 0 , 1 tương ứng với tường và đường đi
    */
    //Lưu tọa độ người chơi
    public static int fxPlayer, fyPlayer;
    public static Target target = new Target(); // Chỉ dùng lưu tọa độ điểm đích
    // Lưu số lượng quân 
    public static int nGhost;
    // Lưu tọa độ ban đầu các xe;
    public static int[][] toaDoGhostBanDau;
    // Lưu giá trị map
    public static int[][] map;
    
    
    public void NhapMapTuFile(String tenFile){
        try( Scanner sc = new Scanner(new File(tenFile))){
            // Lấy tọa độ người chơi
            fxPlayer = sc.nextInt();
            fyPlayer = sc.nextInt();
            // Lấy tọa độ điểm đích;
            target.fxTarget = sc.nextInt();
            target.fyTarget = sc.nextInt();
            // Lấy số lượng quân
            nGhost = sc.nextInt();
            // Lấy tọa độ của những chiếc xe
            toaDoGhostBanDau = new int[nGhost][2];
            for(int i = 0; i < nGhost; i++)
                for(int j = 0; j < 2; j++){
                    toaDoGhostBanDau[i][j] = sc.nextInt();
                }
            map = new int[Config.nCell][Config.nCell];
            for(int i = 0; i < Config.nCell; i++)
                for(int j = 0; j < Config.nCell; j++){
                    map[i][j] = sc.nextInt();
                }
            // Lấy giá trị của map lưu vào file Map
        } catch(Exception ex){
            System.out.printf("Loi gap phai: %s\n",ex.toString());
        }
    }
}
