/**
 * @author Veronica Andrews (vjandrews@wpi.edu)
 * @date 11/15/2021
 * Player class used in KnibbleGameManager
 */

package knibble;

/**
 * Player class, used in KnibbleGameManager to create instances of players.
 * @author vjandrews
 *
 */
public class Player {
	
	private String name;
	private Integer numCoins;
	private Integer guess;
	private boolean isPlaying;
	
	/**
	 * This is the only constructor for a Player instance.
	 * Enter the player name and then the holdings and guess are set to a default that is clearly a default.
	 * isPlaying is automatically set to true.
	 * @param name the name of the player
	 */
	public Player(String name) {
		this.name = name;
		this.numCoins = -99;
		this.guess = -99;
		isPlaying = true;
	}
	
	/**
	 * Gets the name of the player instance.
	 * @return String name of player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the holding of the player
	 * @param num holding for player
	 */
	public void setHolding(Integer num) {
		this.numCoins = num;
	}
	
	/**
	 * gets the holding of the player
	 * @return Integer holding
	 */
	public Integer getHolding() {
		return numCoins;
	}
	
	/**
	 * sets the guess of the player
	 * @param guess current guess
	 */
	public void setGuess(Integer guess) {
		this.guess = guess;
	}
	
	/**
	 * gets the guess of the player
	 * @return Integer guess
	 */
	public Integer getGuess() {
		return guess;
	}
	
	/**
	 * sets isPlaying boolean, which keeps track of if a player is currently in the game
	 * @param p isPlaying boolean
	 */
	public void setIsPlaying(boolean p) {
		this.isPlaying = p;
	}
	
	/**
	 * gets boolean for if a player is playing
	 * @return boolean isPlaying
	 */
	public boolean getIsPlaying() {
		return isPlaying;
	}

}
