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
