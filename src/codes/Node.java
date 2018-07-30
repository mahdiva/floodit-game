package codes;

import codes.Node;

public class Node {
	
	private int data;
	private boolean flooded;
	
	private Node up, down, right, left;
	
	// Initialize the Node variables
	public Node(){
		data = 0;
		flooded = false;
		up = down = right = left = null;
	}
	
	// Overload the constructor
	public Node(int data){
		this.data = data;
	}

	// Getter and Setters for the Node value
	public int getData(){
		return this.data;
	}
	
	public void setData(int data){
		this.data = data;
	}
	
	// Check if the Node is flooded or not
	public boolean isFlood(){
		return this.flooded;
	}
	
	// Setting the flood state of the node
	public void setFlood(boolean state){
		this.flooded = state;
	}
	
	// Getters for the adjacent Nodes
	public Node getUp(){
		return this.up;
	}
	
	public Node getDown(){
		return this.down;
	}
	
	public Node getRight(){
		return this.right;
	}
	
	public Node getLeft(){
		return this.left;
	}
	
	// Setters for the adjacent Nodes
	public void setUp(Node up){
		this.up = up;
	}
	
	public void setDown(Node down){
		this.down = down;
	}
	
	public void setRight(Node right){
		this.right = right;
	}
	
	public void setLeft(Node left){
		this.left = left;
	}
	
}
