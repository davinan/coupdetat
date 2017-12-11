import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom; 

public class Core{

	public static class Player{
		private int lifeOne;
		private int lifeTwo;
		private int numLives;
		private String[] key = {"10", "Jack", "Queen", "King", "Ace"};

		public Player(int i){
			int min = i; 
			int max = 14;

			lifeOne = ThreadLocalRandom.current().nextInt(min, max + 1);
			lifeTwo = ThreadLocalRandom.current().nextInt(min, max + 1);
		}
		public void setNewPlayer(){
			int min = 10; 
			int max = 14;
			this.lifeOne = ThreadLocalRandom.current().nextInt(min, max + 1);
			this.lifeTwo = ThreadLocalRandom.current().nextInt(min, max + 1);
		}
		public int getNumLives(){
			int count = 0;
			if(lifeOne != 0){
				count++;
			}
			if(lifeTwo != 0){
				count++;
			}
			return count;
		}
		private int getFirstCard(){
			return this.lifeOne;
		}
		private int getSecondCard(){
			return this.lifeTwo;
		}
		public String getCards(){
			int one = this.getFirstCard();
			int two = this.getSecondCard();
			String cards = null;
			if(one != 0)
				cards = key[one-10];
			if(two != 0 && cards != null)
				cards += " and " + key[two-10];
			else if(two != 0 && cards == null)
				cards = key[two-10];
			if(cards == null){
				return "No Cards";
			}
			else
				return cards;
		}
		public void attacked(int i){
			if(i == 1){
				this.lifeOne = 0;
			}
			else 
				this.lifeTwo = 0;
		}
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int numPlayers;
		System.out.println("How many players, may I ask?");
		numPlayers = in.nextInt();

		Player[] table = new Player[numPlayers];
		// System.out.println("Bonjour?");

		for(int i = 0; i < numPlayers; i++){
			table[i] = new Player(10);
		}

		System.out.println("Cards on their Hands");
		for(int i = 0; i < numPlayers; i++){
			System.out.print("Player " + (i+1)+ ": ");
			System.out.println(table[i].getCards());
		}	
		System.out.println();
		System.out.println("Round 1: ");
		for(int i = 0; i < numPlayers; i++){
			System.out.print("Player "+ (i+1) + ", who which player would you like to attack? ");
			int attacking = in.nextInt();
			System.out.print("Which card should he discard? (1 / 2)");
			int cardD = in.nextInt();
			table[attacking-1].attacked(cardD);
			System.out.println("Very well, you atacked player " + attacking + " who now has " + table[attacking-1].getNumLives() + " lives");
		}
		System.out.println("After Round 1");
		
		System.out.println();
		System.out.println("Cards on their Hands");
		for(int i = 0; i < numPlayers; i++){
			System.out.print("Player " + (i+1)+ ": ");
			System.out.println(table[i].getCards());
		}	
		System.out.println();

		
	}
}