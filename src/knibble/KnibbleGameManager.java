/*******************************************************************************
 * This files was developed for CS4533: Techniques of Programming Language Translation
 * and/or CS544: Compiler Construction
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2020-21 Gary F. Pollice
 *******************************************************************************/

package knibble;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class for managing Knibble games for the
 * TDD programming assignment.
 */
public class KnibbleGameManager
{
	private List<Player> players = new ArrayList<Player>();
    
	/**
     * This is the only constructor for the game manager.
     * @param players the names of the players in the order 
     * of the first round of play and for when the holdings
     * and guesses are made.
     */
    public KnibbleGameManager(List<String> players)
    {
       players.forEach((player -> this.players.add(new Player(player))));
    }
    
    /**
     * Gets the list of players from the manager.
     * @return list of players
     */
    public List<Player> getPlayers() {
    	return this.players;
    }
    
    /**
     * Enter the holdings for the players remaining. The
     * order of the holdings follow the original list of
     * players when the game was created. If a player has
     * won a round, they do not enter a holding.
     * @param coins the number of coins for each player (0-3)
     */
    public void enterHoldings(List<Integer> coins)
    {
    	
    	// pop first item in coins list and set as holding for current player
    	List<Player> currPlayers = getCurrentPlayers();
    	for(Player player: currPlayers) {
    			player.setHolding(coins.remove(0)); // pop the first element in the coins list
    	}
    	
    }
    
    /**
     * Enter the guesses for the players remaining. The
     * order of the guesses follow the original list of
     * players when the game was created. If a player has
     * won a round, they do not enter a guess.
     * @param guesses the guess for each player
     */
    public void enterGuesses(List<Integer> guesses)
    {
    	//pop first item in guesses list and set as guess for current player
    	List<Player> currPlayers = getCurrentPlayers();
    	for(Player player: currPlayers) {
    			player.setGuess(guesses.remove(0)); // pop the first element in the guesses list
    	}
    }
    
    /**
     * Play the current round and return the result
     * @return "X wins round" if player X wins a round,
     *  "X loses" if player X is the last player in the
     *  game, and "" if there is no
     */
    public String playRound()
    {
    	
    	
        return "A loses";
    }
    
    /**
     * helper function to get players that are currently playing
     * @return list of current players
     */
    private List<Player> getCurrentPlayers() {
    	// iterate through the list of players and check if the player is currently in the game
    	List<Player> currPlayers = new ArrayList<Player>();
    	
    	for(Player player: this.players) {
    		if(player.getIsPlaying()) {
    			currPlayers.add(player);
    		}
    	}
    	
    	return currPlayers;
    }
}
