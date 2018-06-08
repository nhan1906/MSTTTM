/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.thuattoan;

/**
 *
 * @author James
 */
public class Node {
    // Bien kiem tra Node hien tai la diem bat dau , hay muc tieu hay tuong
    private boolean start;
    private boolean target;
    private boolean wall;
    // Tọa độ trong board
    private int fx, fy;
    // Tọa độ thực trong frame
    private int xPos, yPos;
    private Node parent; // Node cha
    private Node children; // Node con
    
    private boolean onPath;// ???
    
    private int H, G, F;
    private boolean open = false;
    private boolean close = false;
    
    public Node(int x, int y){
        initNode(x, y);
    }
    
    private void initNode(int x, int y){
        H = 0;
        G = 0;
        F = 0;
        start = false;
        target = false;
        wall = false;
        children = null;
        parent = null;
        fx = x;
        fy = y;
    }
    
    public void clearNode(){
        start = false;
        target = false;
        wall = false;
        onPath = false;
        children = null;
        parent = null;
    }
    
    public void calcH(Node target){
        H = (int) ((Math.sqrt((target.fx - fx) * (target.fx - fx) + (target.fy - fy) * (target.fy - fy) )) * 10); // ?? 10
        
    }
    
    public int calcG(Node n){
        int temp = n.getG();
        // Nếu node đang xet không phải là Node theo đường chéo
        if(fx != n.fx && fy != n.fy){
            temp += 14;
        }
        else temp += 10;
        return temp;
    }
    
    public int getF() {
        return G + H;
    }
    public void setF(int giatri){
        F = giatri;
    }

    
    
    
    public boolean isStart() {
        return start;
    }

    public boolean isTarget() {
        return target;
    }

    public boolean isWall() {
        return wall;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getFX() {
        return fx;
    }

    public int getFY() {
        return fy;
    }

    public Node getParent() {
        return parent;
    }
    
    public Node getChildren(){
        return children;
    }
    public void setChilden(Node children){
        this.children = children;
    }
    public int getH() {
        return H;
    }

    public int getG() {
        return G;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClose() {
        return close;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    
    
    public Node setX(int x) {
	xPos = x;
	return this;
    }
	
    public Node setY(int y) {
	yPos = y;
	return this;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setH(int H) {
        this.H = H;
    }

    public void setG(int G) {
        this.G = G;
    }

    
    public void setOpen() {
		open = true;
	}
    public void setClosed() {
	close = true;
    }

    public boolean isOnPath() {
        return onPath;
    }

    public void setPath() {
	onPath = true;
    }  
}
