/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.thuattoan;

import java.util.ArrayList;
import program.model.MapGame;
import program.model.Target;
import program.utils.Config;

/**
 *
 * @author James
 */
public class ThuatToan {
    private Node start; // Nút bắt đầu tọa độ của nv
    private Node target; // Nút chứa đích
    // Nút dùng để tìm con từ start -> target
    private Node trunggian;
    private Node trunggian1;
    private Node[][] nodeList;
    
    public ThuatToan(int startFx, int startFy){
        initThuatToan(startFx, startFy);
    }
    
    public void chayThuatToan(){
        caculatePath();
    }
    
    public void initThuatToan(int startFx, int startFy){
        nodeList = new Node[Config.nCell][Config.nCell];
        for (int i = 0; i < Config.nCell; i++)
            for(int j = 0; j < Config.nCell; j++){
                nodeList[i][j] = new Node(i,j).setX(i * Config.hCell).setY(j * Config.wCell);
            }
        
        start = new Node(startFx, startFy);
        target = new Node(Target.fxTarget, Target.fyTarget);
        for (int i = 0; i < Config.nCell; i++)
            for(int j = 0; j < Config.nCell; j++){
                nodeList[i][j].calcH(target);
                if(MapGame.map[i][j] == 1)
                    nodeList[i][j].setWall(true);
                else
                    nodeList[i][j].setWall(false);
            }
        
        trunggian = start;
    }
            // hàm này sẽ xóa toàn bộ những dữ liệu đã có trong nodeList, để trở lại ban đầu
    public void clear(){                                   
        start = null;
        target = null;
        trunggian = null;
        for(int i = 0; i< Config.hCell; i++)
            for(int j = 0; j < Config.wCell; j++)
                nodeList[i][j].clearNode();
    }

    //Trong game thì posW tính theo chiêu ngang, posH tính theo chiều dọc
    // Còn với mảng, nodeList[i][j] , b[i][j]  thì i tính theo chiều dọc, j tính theo chiều ngang
    private void caculatePath() {
        ArrayList<Node> closed = new ArrayList<>();
        ArrayList<Node> open = new ArrayList<>();
        
        Node currentNode = start;
        
        // Mở nút ban đầu
        open.add(currentNode);
        // Open vẫn chứa node thì thực hiện
        while(!open.isEmpty()){
            // Remove open
            open.remove(currentNode);
            // Thêm nó vào closed
            closed.add(currentNode);
            // Nếu node hiện tại là đích thì kết thúc
            if(currentNode == target)
                break;
            // Xét các giá trị lân cận
            // Theo chiều dọc
            for( int i = currentNode.getFX() - 1; i <= currentNode.getFX() + 1; i++){
                if(i < 0 || i > Config.nCell - 1)
                    continue;
                for(int j = currentNode.getFY() - 1; j <= currentNode.getFY() + 1; j++) { // Xét theo chiều ngang
                    if(j < 0 || j > Config.nCell - 1)
                    continue;
                    
                    //Loại bỏ node chéo 
                    if(i != currentNode.getFX() && j != currentNode.getFY())
                        continue;
                    //Loại bỏ node chính nó
                    if(i == currentNode.getFX() && j == currentNode.getFY())
                        continue;
                    // Tường đảo ngược
                    if(nodeList[j][i].isWall()){
                        continue;
                    }
                    
                    // Sau khi loại bỏ các node không hợp lí tiến hành kiểm tra
                    Node node = nodeList[i][j];
                    // Node không tồn tại trong open và close
                    if(!open.contains(node) && ! closed.contains(node)){
                        // Cập nhật G cho node
                        node.setG(node.calcG(currentNode));
                        node.setF(node.getF());
                        node.setParent(currentNode);
                        open.add(node);
                    }
                    else if(open.contains(node)){
                        // open chứa node
                        if(node.getG() > node.calcG(currentNode)){
                            node.setG(node.calcG(currentNode));
                            node.setF(node.getF());
                            node.setParent(currentNode);
                        }
                    }
                    else if(closed.contains(node)){
                        if(node.getG() > node.calcG(currentNode)){
                            node.setG(node.calcG(currentNode));
                            node.setF(node.getF());
                            node.setParent(currentNode);
                            closed.remove(node);
                            open.add(node);
                        }
                    }
                    
                    
                    // Tìm ra node có ước lượng thất nhất
                    currentNode = minOfList(open);
                    
                }
            }
        }
        
    }
    
    //Hàm tìm nút có giá trị F nhỏ nhất trong danh sách
    public Node minOfList(ArrayList<Node> open) {
		
		Node n = open.get(0);
		for (Node node : open) {
			if(node.getF() < n.getF()) {
				n = node;
			}
                }
		return n;
    }
    
    public void TimCon(){        //ngược lại với tìm cha, thì bây giờ tìm con
        trunggian = target;
        trunggian1 = target;
        while(trunggian1 != start){
            trunggian1 = trunggian.getParent();
            trunggian1.setChilden(trunggian);
            trunggian = trunggian1;
        }
        trunggian = start;     //trả lại trung gian = start ban đầu
    }
   
    

    
    
    public void resetNode() {		
	target = null;
	start = null;
	for(int i = 0;  i < Config.nCell; i++)
	    for(int j = 0; j < Config.nCell; j++) {
		nodeList[i][j] = new Node(i, j).setX(i*Config.wCell).setY(j*Config.hCell);
	    }
    }
    
    
    public Node getStart() {
        return start;
    }

    public Node getTarget() {
        return target;
    }

    public Node getTrunggian() {
        return trunggian;
    }

    public Node getTrunggian1() {
        return trunggian1;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public void setTrunggian(Node trunggian) {
        this.trunggian = trunggian;
    }

    public void setTrunggian1(Node trunggian1) {
        this.trunggian1 = trunggian1;
    }

    public void setNodeList(Node[][] nodeList) {
        this.nodeList = nodeList;
    }
     public Node[][] getNodeList() {
        return nodeList;
    }
}
