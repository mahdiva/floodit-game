package codes;

import java.util.Scanner;

public class Game {
	
	private static Scanner input;

	public static void main(String[] args) {
		
		// Setting up the variables
		int choice = 0, tries = 1, maxTries = 25, playing = 1, highScore = 0;
		
		input = new Scanner(System.in);
		
		// Main board of the game
		LinkedGrid game = new LinkedGrid(14, 14);
		
		do{
			
			// Print the board
			game.display();
			
			// When the board has been completely flooded
			if(game.isSolved()){
				if(tries < highScore || highScore == 0){
					highScore = tries;
				}
				System.out.println("You Won!");
				System.out.println("Play again (1=Yes, 0=No)?");
				playing = getInput();
				
				tries = 1;
				
				// Reset the board and the game, if the player is continuing to paly again
				if(playing == 1){
					game.restartBoard();
					game.display();
				}else{ // If the player is not playing anymore, break out of the loop
					System.out.println("Thanks for playing!");
					break;
				}
			}
			
			// If the player has played more than max allowed turns
			if(tries > maxTries){
				System.out.println("You lose, How many moves will it take you?");
			}
			
			System.out.println("High Score: " + ((highScore==0) ? "None" : highScore) );
			System.out.println("Turn: " + tries + "/" + maxTries);
			
			System.out.print("Your Choice: ");
			
			// Get input from the user
			choice = getInput();
			
			System.out.println();
			game.flood(choice);
			
			//game.displayFlood(); // For debugging purposes
			
			tries++;

			
		}while( playing == 1 );
		
		// Close the input scanner to prevent memory leak
		input.close();
		
	}
	
	// Method for taking the user input and error-trapping it
	public static int getInput(){
		
		int num = 0;
		boolean valid = true;
		do {
			try{
				num = input.nextInt();
				
				// If the number is in the range
				if(num >= 0 && num <= 6){
					
					valid = true;
					
				}else{
					valid = false;
					System.out.println("Invalid Entry\nTry Again: ");
					continue;
				}
				
			} catch(Exception e){
				
				System.out.println("Invalid Entry\nTry Again: ");
				input.next();
				valid = false;
				
			}
			
		} while(!valid);
		
		return num;
	}

}
