/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;
import java.util.List;
import minmax.GameState;

/**
 *
 * @author hermano
 */
public class TicTacToeState implements GameState{

    
    String stateString = "";
    String winner = "_";
    double eval = 0.0;
    ArrayList<GameState> children = new ArrayList<>();
    GameState parent;
    
    public TicTacToeState(String stateString)
    {
        this.stateString = stateString;
    }
    
    @Override
    public ArrayList<GameState> getChildren() {
        return children;
    }

    @Override
    public GameState getParent() {
        return parent;
    }

    @Override
    public void addChild(GameState childState) {
        children.add(childState);
    }

    @Override
    public void addParent(GameState parentState) {
        this.parent = parentState;
    }

    @Override
    public double getEvaluation() {
        if(!isFinalGameState())
            return eval;
                    
        if(winner.equals("x"))
        {
            eval = 1;
        }
        else if(winner.equals("o")) 
        {
            eval = -1;
        }
        else
        {
            eval = 0;
        }
        
        return eval;
    }

    @Override
    public void setEvaluation(double eval) {
        this.eval = eval;
    }
    
    @Override
    public boolean isFinalGameState() {
        
            
        for(int i = 0; i < 3; i++)
        {
            if(stateString.charAt(i)== '_')
                continue;
                
            if((stateString.charAt(i) == stateString.charAt(i+3))
                && (stateString.charAt(i+3) == stateString.charAt(i+6)))
            {
                winner = stateString.charAt(i)+"";
                return true;    
            }    
                
        }
        
        for(int i = 0; i < 9; i+=3)
        {
            if(stateString.charAt(i)== '_')
                continue;
                
            if((stateString.charAt(i) == stateString.charAt(i+1))
                && (stateString.charAt(i+1) == stateString.charAt(i+2)))
            {
                winner = stateString.charAt(i)+"";
                return true;    
            }    
        }
        
        if(stateString.charAt(0) != '_')
            if((stateString.charAt(0) == stateString.charAt(4))
                  && (stateString.charAt(4) == stateString.charAt(8)))
            {
                winner = stateString.charAt(0)+"";
                return true;    
            }
        
        if(stateString.charAt(2) != '_')
            if((stateString.charAt(2) == stateString.charAt(4))
                  && (stateString.charAt(4) == stateString.charAt(6)))
            {
                winner = stateString.charAt(2)+"";
                return true;    
            }
        
        if(!stateString.contains("_"))
            return true;
        
        return false;
    }

    @Override
    public GameState getInitialState() {
        return new TicTacToeState("_________");
    }
    
    @Override
    public String toString(){
        return stateString;
    }
    
    @Override
    public String toFormattedString(){
        
        String formmatedString = "";
        
        for(int i = 1; i <= 9; i++)
        {
            formmatedString += stateString.charAt(i-1 );
            if(i % 3 == 0)
                formmatedString += '\n';
        }
        
        return formmatedString;
    }
    
}
