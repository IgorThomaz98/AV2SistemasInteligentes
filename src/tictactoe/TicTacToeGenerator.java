/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.HashMap;
import minmax.GameState;
import minmax.Player;
import minmax.SuccessorGenerator;

/**
 *
 * @author hermano
 */
public class TicTacToeGenerator implements SuccessorGenerator {

    @Override
    public void generateSuccessors(GameState state, Player player) 
    {
        String stateString = state.toString();
        char mark = (player == Player.PLAYER_MAX) ? 'x' : 'o';
        
        for(int i = 0; i < stateString.length(); i++)
        {
            char[] stateCharArray = stateString.toCharArray();
            if(stateCharArray[i] == '_')
            {
                stateCharArray[i] = mark;
                state.addChild(new TicTacToeState(String.valueOf(stateCharArray)));
            }   
        }
    }
    
}
