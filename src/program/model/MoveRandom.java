/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.model;

/**
 *
 * @author James
 */
// Hàm này thực hiện nhiệm vụ khi không có đích
// sẽ thực hiện tìm các hướng có thể đi được và di chuyển ngẫu nhiên
public interface MoveRandom {
    
    // Tìm hướng đi phù hợp cho nhân vật
    // Random các hướng đi có thê
    public int findDirectionNextMove(int fx, int fy);
    // Hàm thực hiện di chuyển một đoạn theo hướng
    public int distanceCanMove(int fx,int fy,int dir);
    
}
