package knibble;

public class Player {
	
	private String name;
	private Integer numCoins;
	private Integer guess;
	private boolean isPlaying;
	
	public Player(String name) { // Initialize a player
		this.name = name;
		this.numCoins = -99;
		this.guess = -99;
		isPlaying = true;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHolding(Integer num) {
		this.numCoins = num;
	}
	
	public Integer getHolding() {
		return numCoins;
	}
	
	public void setGuess(Integer guess) {
		this.guess = guess;
	}
	
	public Integer getGuess() {
		return guess;
	}
	
	public void setIsPlaying(boolean p) {
		this.isPlaying = p;
	}
	
	public boolean getIsPlaying() {
		return isPlaying;
	}

}
