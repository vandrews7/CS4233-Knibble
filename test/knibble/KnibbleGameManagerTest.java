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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

/**
 * Test cases for the KnibbleGameManager.
 */
class KnibbleGameManagerTest
{
    @Test
    void createNewGame()
    {
        assertNotNull(new KnibbleGameManager(makePlayers("A", "B")));
    }
    
    @Test
    void enterHoldingsFor2Players()
    {
        KnibbleGameManager manager = new KnibbleGameManager(makePlayers("A", "B"));
        manager.enterHoldings(makeHoldings(2, 0));
        assertTrue(true);
    }
    
    @Test
    void enterFirstRoundInfoFor2Players()
    {
        KnibbleGameManager manager = new KnibbleGameManager(makePlayers("A", "B"));
        manager.enterHoldings(makeHoldings(2, 0));
        manager.enterGuesses(makeGuesses(3, 2));
        assertTrue(true);
    }
    
    /**
     * This test checks the adding players functionality.
     */
    @Test
    void checkAddPlayers()
    {
    	KnibbleGameManager manager = new KnibbleGameManager(makePlayers("PlayerOne", "PlayerTwo"));
    	String[] playerNames = {"PlayerOne", "PlayerTwo"};
    	List<Player> players = manager.getPlayers();
    	
    	players.forEach((player -> assertEquals(playerNames[players.indexOf(player)], player.getName())));
    }
    
    /**
     * This test checks the enterHoldings functionality.
     */
    @Test
    void checkEnterHoldings()
    {
    	KnibbleGameManager manager = new KnibbleGameManager(makePlayers("PlayerOne", "PlayerTwo"));
    	Integer[] holdings = {3, 2};
    	manager.enterHoldings(makeHoldings(3, 2));
    	List<Player> players = manager.getPlayers();
    	
    	
    	players.forEach((player -> assertEquals(holdings[players.indexOf(player)], player.getHolding())));
    }
    
    /**
     * This test checks the enterGuesses functionality.
     */
    @Test
    void checkEnterGuesses()
    {
    	KnibbleGameManager manager = new KnibbleGameManager(makePlayers("PlayerOne", "PlayerTwo"));
    	Integer[] guesses = {4, 3};
    	manager.enterGuesses(makeGuesses(4, 3));
    	List<Player> players = manager.getPlayers();
    	
    	
    	players.forEach((player -> assertEquals(guesses[players.indexOf(player)], player.getGuess())));
    }
    
    /**
     * This test is for a 2 player game
     * @param players list of players
     * @param expected list of expected round outcomes
     * @param rounds list of round inputs: holdings and guesses
     */
    @ParameterizedTest
    @MethodSource("gameProvider")
    void playGame(List<String>players, String expected, List<RoundInput> rounds)
    {
        KnibbleGameManager manager = new KnibbleGameManager(players);
        String result = null;
        for (RoundInput ri : rounds) {
            manager.enterHoldings(ri.holdings);
            manager.enterGuesses(ri.guesses);
            result = manager.playRound();
        }
        assertEquals(expected, result);
    }
    
    /**
     * This test is for a 3 player game
     * @param players list of players
     * @param expected list of expected round outcomes
     * @param rounds list of round inputs: holdings and guesses
     */
    @ParameterizedTest
    @MethodSource("multiRoundGameProvider1")
    void playMultiRoundGame(List<String> players, List<String> expected, List<RoundInput> rounds)
    {
    	KnibbleGameManager manager = new KnibbleGameManager(players);
    	List<String> results = new ArrayList<>();
    	for(RoundInput ri: rounds) {
    		manager.enterHoldings(ri.holdings);
    		manager.enterGuesses(ri.guesses);
    		results.add(manager.playRound());
    	}
    	assertEquals(expected, results);
    }
    
    /**
     * This test is for when no players guess the right answer on the first round
     * @param players list of players
     * @param expected list of expected round outcomes
     * @param rounds list of round inputs: holdings and guesses
     */
    @ParameterizedTest
    @MethodSource("multiRoundGameProvider2")
    void playMultiRoundGame2(List<String> players, List<String> expected, List<RoundInput> rounds)
    {
    	KnibbleGameManager manager = new KnibbleGameManager(players);
    	List<String> results = new ArrayList<>();
    	for(RoundInput ri: rounds) {
    		manager.enterHoldings(ri.holdings);
    		manager.enterGuesses(ri.guesses);
    		results.add(manager.playRound());
    	}
    	assertEquals(expected, results);
    }
    
    
    /**************************** Providers ****************************/
    static Stream<Arguments> gameProvider()
    {
        return Stream.of(
            arguments(makePlayers("A", "B"), "A loses", makeRoundInputs(
                new RoundInput(makeHoldings(2, 0), makeGuesses(3, 2))
                )),
            arguments(makePlayers("A", "B"), "B loses", makeRoundInputs(
                new RoundInput(makeHoldings(2, 0), makeGuesses(2, 3))
                ))
        );
    }
    
    static Stream<Arguments> multiRoundGameProvider1()
    {
        return Stream.of(
            arguments(makePlayers("A", "B", "C"), makeExpectedOutcomes("C wins round", "A loses"), makeRoundInputs(
                new RoundInput(makeHoldings(2, 0, 1), makeGuesses(1, 2, 3)),
                new RoundInput(makeHoldings(1, 3), makeGuesses(4, 2))
                )),
            arguments(makePlayers("A", "B", "C"), makeExpectedOutcomes("A wins round","B loses"), makeRoundInputs(
                new RoundInput(makeHoldings(2, 0, 1), makeGuesses(3, 2, 1)),
                new RoundInput(makeHoldings(1, 3), makeGuesses(2, 4))
                ))
        );
    }
    
    static Stream<Arguments> multiRoundGameProvider2()
    {
        return Stream.of(
            arguments(makePlayers("A", "B", "C"), makeExpectedOutcomes("", "B wins round", "C loses"), makeRoundInputs(
                new RoundInput(makeHoldings(2, 0, 1), makeGuesses(1, 2, 4)),
                new RoundInput(makeHoldings(1, 2, 0), makeGuesses(3, 1, 2)),
                new RoundInput(makeHoldings(1, 3), makeGuesses(2, 4))
                ))
        );
    }
    
    /**************************** Helper Methods ****************************/
    static List<Integer> makeHoldings(Integer... holdings)
    {
        return new ArrayList<>(Arrays.asList(holdings));
    }
    
    static List<Integer> makeGuesses(Integer... guesses)
    {
        return new ArrayList<>(Arrays.asList(guesses));
    }
    
    static List<String> makePlayers(String... players)
    {
        return new ArrayList<>(Arrays.asList(players));
    }
    
    static List<RoundInput> makeRoundInputs(RoundInput... inputs)
    {
        return new ArrayList<>(Arrays.asList(inputs));
    }
    /**
     * This method creates an array of expected outcomes
     * @param outputs expected output strings
     * @return list of strings inputted
     */
    static List<String> makeExpectedOutcomes(String...outputs)
    {
    	return new ArrayList<>(Arrays.asList(outputs));
    }
}

class RoundInput
{
    final List<Integer> holdings;
    final List<Integer> guesses;
    
    public RoundInput(List<Integer> holdings, List<Integer> guesses)
    {
        this.holdings = holdings;
        this.guesses = guesses;
    }
}
