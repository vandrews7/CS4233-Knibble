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

/**
 * Veronica Andrews (vjandrews)
 */
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
	private int totalNumCoins = 0;
	
	/**
	 * This enum is used for a helper function to switch between entering holdings or entering guesses.
	 */
	enum Info{
		HOLDING,
		GUESS
	}
    
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
    	this.totalNumCoins = coins.stream().mapToInt(Integer::intValue).sum();
    	enterInfo(Info.HOLDING, coins);
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
    	enterInfo(Info.GUESS, guesses);
    }
    
    /**
     * Play the current round and return the result
     * @return "X wins round" if player X wins a round,
     *  "X loses" if player X is the last player in the
     *  game, and "" if there is no
     */
    public String playRound()
    {
    	// get list of current players and number of current players
    	List<Player> currPlayers = getCurrentPlayers();
    	int numCurrPlayers = currPlayers.size();
    	
    	// iterate through current players, check guess vs total number of coins
    	// if player guesses correctly, player wins round and stops playing -> "<Player> wins the round"
    	// when there is only one player left -> "<player> loses"
    	// otherwise ""
    	for(Player player: currPlayers) {
    		if(player.getGuess() == totalNumCoins && numCurrPlayers != 1) {
        			// player wins, remove player, break
        			player.setIsPlaying(false);
        			numCurrPlayers--;
        			if(numCurrPlayers > 1) {
        				rotatePlayers();
        				return player.getName() + " wins round";
        			}
    		}
//    		else if (numCurrPlayers == 1) { //shortcut if the last player is after the winner in the list
//    			return player.getName() + " loses";
//    		}
    	}
    	// if there is one player left, find which one it is
    	if(numCurrPlayers == 1) {
    		for(Player player: currPlayers) {
    			if(player.getIsPlaying()) {
    				return player.getName() + " loses";
    			}
    		}
    	}
    	
    	rotatePlayers();
    	return "";

    	
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
    
    /**
     * This helper function reduces duplicate code in the enterGuesses and enterHoldings functions.
     * @param type enum used to distinguish what type of info is being entered
     * @param list list of info that will be added to the current players
     */
    private void enterInfo(Info type, List<Integer> list) {
    	//pop first item in list and set as specific info for current player based on enum input
    	List<Player> currPlayers = getCurrentPlayers();
    	for(Player player: currPlayers) {
    		int i = currPlayers.indexOf(player);
    		switch(type) {
    			case HOLDING:
    				this.players.get(i).setHolding(list.remove(0)); // pop the first element in the coins list
    				break;
    			case GUESS:
    				this.players.get(i).setGuess(list.remove(0)); // pop the first element in the guesses list
    				break;
    		}
    	}
    }
    
    /**
     * This function pops the first player off the list and adds it to the end of the list preparing for the next round
     */
    private void rotatePlayers() {
    	players.add(players.remove(0));
    }
}
