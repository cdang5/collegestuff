/*
File: Player.java
Used to keep track of a players stats.
*/

public class Player {
    private int name;
    private int wins;
    private int streak;
    private int matches;
    private int guess;

    public Player(int name) {
	this.name = name;
	this.matches = 0;
	this.guess = 0;
	this.streak = 0;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getMatches() {
        return matches;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public int getGuess() {
        return guess;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getWins() {
        return wins;
    }
}
