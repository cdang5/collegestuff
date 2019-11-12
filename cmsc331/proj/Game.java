/*
 *File: Game.java
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Game {
    private boolean p1turn = true;
    //private boolean keepGoing = true;
    //Deck of cards
    private  ArrayList<Card> deck = new ArrayList<>(52);
    //Deck of cards that have been seen
    private ArrayList<Card> seenCards = new ArrayList<>();
    private Player player1 = new Player(1);
    private Player player2 = new Player(2);
    private static PrintWriter writer= null;


    public Game(){
    }

    public void createDeck(){
        for(int suit = 0; suit < 4; suit++){
            for(int rank = 0; rank < 13; rank ++){
                Card currCard ;
                currCard = new Card(rank, suit);
                deck.add(currCard);
            }
        }
	//   writer.println("ASDSDASDA");
    }

    public void shuffle(){
        ArrayList<Card> temp = new ArrayList<Card>();
        while(!deck.isEmpty()){
            int n = (int)(Math.random() * deck.size());
            temp.add(deck.get(n));
            deck.remove(n);
        }
        deck = temp;
    }

    public void completelyRandom(){
        player1.setGuess(player1.getGuess()+1);
        Random rand = new Random();

	int guess1 = rand.nextInt(deck.size());
        int guess2 = rand.nextInt(deck.size());

        //Numbers are not the same, and are unmatched
        while(guess1 == guess2 ||
	      Objects.equals(deck.get(guess1).getFaced(), "XX") ||
	      Objects.equals(deck.get(guess2).getFaced(), "XX")) {
            guess1 = rand.nextInt(deck.size());
            guess2 = rand.nextInt(deck.size());
        }

        System.out.print("GUESS1: "+guess1);
        writer.print("GUESS1: "+guess1);
        System.out.println("  GUESS2: "+guess2);
        writer.println("  GUESS2: "+guess2);
        deck.get(guess1).setFoundSomething(true);
        deck.get(guess2).setFoundSomething(true);

        displayBoard();

	if(Objects.equals(deck.get(guess1).getNum(), deck.get(guess2).getNum()) &&
	   Objects.equals(deck.get(guess1).getColor(), deck.get(guess2).getColor())){

            System.out.println("Congrats! You matched the cards "+ deck.get(guess1) + " and " + deck.get(guess2));
            writer.println("Congrats! You matched the cards "+ deck.get(guess1) + " and " + deck.get(guess2));
            player1.setMatches(player1.getMatches() + 1);

            deck.get(guess1).setFaced("XX");
            deck.get(guess2).setFaced("XX");
        }

	else{
            //Adds the two seen cards into another Card array
            seenCards.add(deck.get(guess1));
            seenCards.add(deck.get(guess2));

            p1turn = false;
            System.out.println("No match");
            writer.println("No match");
        }

        deck.get(guess1).setFoundSomething(false);
        deck.get(guess2).setFoundSomething(false);

    }

    //Strategy: Gets last card drawn and a random card
    public void firstSeen() {
        Random rand = new Random();
        player2.setGuess(player2.getGuess() + 1);
        Card findThis;
        int guess1;

	//Gets the last card turned over
        //Checks to see if there are seen cards to flip over
        //If not choose a random card
        if (seenCards.size() >= 1){
            findThis = seenCards.get(seenCards.size() - 1);
            guess1 = deck.indexOf(findThis);
        }
        else{
            guess1 = rand.nextInt(deck.size());
        }


        System.out.print("GUESS1: "+guess1);
        writer.print("GUESS1: "+guess1);
        //The next card is random

        int guess2 = rand.nextInt(deck.size());
        //Checks if they're the same, or if the second card is already matched
        while(guess1 == guess2 || Objects.equals(deck.get(guess2).getFaced(), "XX")) {
            guess2 = rand.nextInt(deck.size());
        }
        System.out.print("  GUESS2: "+guess2);
        writer.print("  GUESS2: "+guess2);

        deck.get(guess1).setFoundSomething(true);
        deck.get(guess2).setFoundSomething(true);

        displayBoard();

	if((Objects.equals(deck.get(guess1).getNum(), deck.get(guess2).getNum())) &&
	   (Objects.equals(deck.get(guess1).getColor(), deck.get(guess2).getColor()))){

            System.out.println("Congrats! You matched the cards "+ deck.get(guess1) + " and " + deck.get(guess2));
            writer.println("Congrats! You matched the cards "+ deck.get(guess1) + " and " + deck.get(guess2));
            deck.get(guess1).setFaced("XX");
            deck.get(guess2).setFaced("XX");

            player2.setMatches(player2.getMatches() + 1);
            seenCards.remove(seenCards.size()-1);
        }

        else{
            //Adds the seen card into another Card array
            seenCards.add(deck.get(guess2));
            p1turn = true;
            System.out.println("No match");
            writer.println("No match");
        }

	deck.get(guess1).setFoundSomething(false);
        deck.get(guess2).setFoundSomething(false);
    }

    public void displayBoard(){
        for(int c = 0; c < deck.size(); c++){
            if(c % 13 == 0) {
                System.out.println();
                writer.println();
            }
            System.out.print(deck.get(c).display()+ " ");
            writer.print(deck.get(c).display()+ " ");
        }
        System.out.println();
        writer.println();
    }

    public boolean checkFull(){
        if(player1.getMatches() + player2.getMatches() == 26)
            return true;
        else
            return false;
    }

    public String winner(){
        if(player1.getMatches() > player2.getMatches()) {
            return "Player 1 wins with "+ player1.getMatches()+ " matches";
        }
        else if(player1.getMatches() < player2.getMatches()) {
            return "Player 2 wins with " + player2.getMatches()+ " matches";
        }
        else
            return "It was a tie.";
    }

    public void playGame(int numG) {
        //System.out.println("GAME: " + numG);
        //System.out.println("=====================");
        player1.setMatches(0);
        player2.setMatches(0);

        checkFull();
        while (!checkFull()) {
            while (p1turn) {
                if (checkFull())
                    break;
                else {
                    checkFull();
                    System.out.println("Player1's turn");
                    writer.println("Player1's turn");
                    completelyRandom();
                    //System.out.println("PLAYER 1 MATCHES: " + player1.getMatches());
                    System.out.println();
                    writer.println();
                }
            }
            if (checkFull())
                break;
            else {
                checkFull();
                System.out.println("Player2's turn");
                writer.println("Player2's turn");
                firstSeen();
                //System.out.println("PLAYER 2 MATCHES: " + player2.getMatches());
                System.out.println();
                writer.println();
            }
        }
        System.out.println(winner());
        writer.println(winner());
        /*System.out.println("Matches by Player1: "+ player1.getMatches());
        writer.println("Matches by Player1: "+ player1.getMatches());
        System.out.println("Matches by Player2: "+ player2.getMatches());
        writer.println("Matches by Player2: "+ player2.getMatches());
        System.out.println("Guesses by Player1: "+ player1.getGuess());
        writer.println("Guesses by Player1: "+ player1.getGuess());
        System.out.println("Guesses by Player2: "+ player2.getGuess());
        writer.println("Guesses by Player2: "+ player2.getGuess());*/
    }

    public void clearDeck(){
        for(int i = 0; i < deck.size(); i++){
            deck.remove(i);
        }
    }

    private static PrintWriter createFile(String filename){
        try{
            writer = new PrintWriter(filename);
        }
        catch(FileNotFoundException e){
            System.out.println("I/O error");
            System.exit(0);
        }
        return null;
    }

    public static void main(String args[]){
        String fileName = "out.txt";

        createFile(fileName);

        Random rand = new Random();
        int numGames = rand.nextInt(40) + 1;
        //Goes through the amount of games (1-40)
        //int numGames = 1;
        System.out.println("Number of games: "+numGames);
        writer.println("Number of games: "+numGames);
        for(int i = 1; i <= numGames; i++) {
            Game start = new Game();
            start.createDeck();
            start.shuffle();
            System.out.println("GAME: " + i);
            System.out.println("=====================");
            start.playGame(i);
            start.clearDeck();
            System.out.println("END OF GAME "+i);
            writer.println("END OF GAME "+i);
        }
        writer.close();
    }
}
