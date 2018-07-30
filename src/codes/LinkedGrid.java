package codes;

import codes.Node;

public class LinkedGrid {
	
	private Node root;
	private int min = 1;
	private int max = 6;
	
	public LinkedGrid(int cols, int rows){
		
		root = new Node( rand(min, max) );
		
		// Add root Node to the flooded list
		root.setFlood(true);
		
		Node rowPointer = root;
		
		for(int y=0; y<rows; y++){
			if(y != 0){
				// Add new Node to the bottom of the column
				Node temp = new Node( rand(min, max) );
				rowPointer.setDown(temp);
				temp.setUp(rowPointer);
				rowPointer = temp;
			}
			
			Node colPointer = rowPointer; // Pointing to the first(1st column) Node in the row
			
			// Add all of the Nodes for that row
			for(int x=1; x<cols; x++){
				Node colTemp = new Node( rand(min, max) );
				colPointer.setRight(colTemp);
				colTemp.setLeft(colPointer);
				if(y != 0){
					// Set up and down pointers
					colTemp.setUp(colTemp.getLeft().getUp().getRight());
					colTemp.getLeft().getUp().getRight().setDown(colTemp);
				}
				colPointer = colTemp;
			}
		}
		
		// Initially flood the game to the initial value of the root Node
		flood( root.getData() );
		
	}
	
	
	// Method for printing the Linked Grid
	public void display(){
		Node rowPointer = root;
		
		while(rowPointer != null){
			Node colPointer = rowPointer;
			
			while(colPointer != null){
				System.out.print(colPointer.getData() + " ");
				colPointer = colPointer.getRight();
			}
			
			System.out.println();
			rowPointer = rowPointer.getDown();
		}
		
		System.out.println();
		System.out.println();
	}
	
	// Method for restarting the game and setting each Node to a new random value
	public void restartBoard(){
		Node rowPointer = root;
		
		while(rowPointer != null){
			Node colPointer = rowPointer;
			
			while(colPointer != null){
				colPointer.setData( rand(min, max) );
				colPointer.setFlood(false); // Set each Node's state to not flooded
				colPointer = colPointer.getRight();
			}
			
			rowPointer = rowPointer.getDown();
		}
		
		root.setFlood(true); // Add root to the flooded list
		
	}
	
	
	// Method for generating a random number in the specified range
	public static int rand(int min, int max){
		return (int)(Math.random() * (max - min + 1) + min);
	}
	
	
	 /*
	 	Method for flooding the given Node:
	 	- Find all of the adjacent Nodes recursively 
	 	- Set their value to the user input
	 	- Set their flooded state to true
	 */
	
	public void floodNode(Node tail, int num){
		
		tail.setData(num);
		
		// Check adjacent Nodes in all 4 directions:
		
		// Right adjacent node
		if( tail.getRight() != null ){
			if( tail.getRight().getData() == num && !tail.getRight().isFlood() ){
				tail.getRight().setFlood(true);
				floodNode(tail.getRight(), num);
			}
			if( tail.getRight().isFlood() && tail.getRight().getData() != num ){
				floodNode(tail.getRight(), num);
			}
		}
		
		// Top adjacent node
		if( tail.getUp() != null ){
			if( tail.getUp().getData() == num && !tail.getUp().isFlood() ){
				tail.getUp().setFlood(true);
				floodNode(tail.getUp(), num);
			}
			if( tail.getUp().isFlood() && tail.getUp().getData() != num ){
				floodNode(tail.getUp(), num);
			}
		}
		
		// Left adjacent node
		if( tail.getLeft() != null ){
			if( tail.getLeft().getData() == num && !tail.getLeft().isFlood() ){
				tail.getLeft().setFlood(true);
				floodNode(tail.getLeft(), num);
			}
			if( tail.getLeft().isFlood() && tail.getLeft().getData() != num ){
				floodNode(tail.getLeft(), num);
			}
		}
		
		// Bottom adjacent node
		if( tail.getDown() != null ){
			if( tail.getDown().getData() == num && !tail.getDown().isFlood() ){
				tail.getDown().setFlood(true);
				floodNode(tail.getDown(), num);
			}
			if( tail.getDown().isFlood() && tail.getDown().getData() != num ){
				floodNode(tail.getDown(), num);
			}
		}
		
	}
	
	
	// Main method for flooding the game board based on the user input
	public void flood(int num){
		
		floodNode(root, num);
		
	}
	
	// Method for checking if the board is solved and the player has won
	public boolean isSolved(){
		
		Node rowPointer = root;
		int rootVal = root.getData();
		
		while(rowPointer != null){
			Node colPointer = rowPointer;
			
			while(colPointer != null){
				if(rootVal != colPointer.getData()){
					return false;
				}
				colPointer = colPointer.getRight();
			}
			System.out.println();
			rowPointer = rowPointer.getDown();
		}
		
		return true;
	}
	
	
	// Method for showing the flood chain, used for debugging
	public void displayFlood(){
		
		Node rowPointer = root;
		
		while(rowPointer != null){
			Node colPointer = rowPointer;
			
			while(colPointer != null){
				if(colPointer.isFlood()){
					System.out.print("* ");
				}else{
					System.out.print("- ");
				}
				
				colPointer = colPointer.getRight();
			}
			System.out.println();
			rowPointer = rowPointer.getDown();
		}
		
		System.out.println();
		System.out.println();
	}
	

}
