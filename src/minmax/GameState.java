/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hermano
 */
public interface GameState {
    public ArrayList<GameState> getChildren();
    public GameState getParent();
    public void addChild(GameState childState);
    public void addParent(GameState parentState);
    public double getEvaluation();
    public void setEvaluation(double eval);
    public boolean isFinalGameState();
    public GameState getInitialState();
    public String toFormattedString();
}
