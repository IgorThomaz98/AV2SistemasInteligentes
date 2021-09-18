package tictactoe;

import java.util.ArrayList;
import java.util.List;
import minmax.GameState;

public class NewGameState implements GameState{

    String stateString = "";
    String winner = "_";
    double eval = 0.0;
    ArrayList<GameState> children = new ArrayList<>();
    GameState parent;

    public NewGameState(String stateString)
    {
        this.stateString = stateString;
    }

    public ArrayList<GameState> getChildren() {
        return children;
    }

    public GameState getParent() {
        return parent;
    }

    public void addChild(GameState childState) {
        children.add(childState);
    }

    public void addParent(GameState parentState) {
        this.parent = parentState;
    }

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
    public void setEvaluation(double eval) { this.eval = eval; }

    @Override
    public boolean isFinalGameState() {

        char win = 'z';

        for(int i = 0; i < 4; i++)
        {
            if(stateString.charAt(i) == '_')
                continue;

            if(stateString.charAt(i) != '_'){
                win = stateString.charAt(i);
                continue;
            }
        }

        for(int i = 8; i != 4; i--)
        {
            if(stateString.charAt(i) == '_')
                continue;

            if(stateString.charAt(i) != '_'){
                win = stateString.charAt(i);
                continue;
            }
        }

        for(int i = 0; i < 8; i++){

            if(stateString.charAt(i) == '_')
                continue;

            if(stateString.charAt(i) != '_'){
                if(win == stateString.charAt(i)){
                    winner = stateString.charAt(i)+"";
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public GameState getInitialState() { return new NewGameState("_________"); }

    public String toString(){
        return stateString;
    }

    @Override
    public String toFormattedString() {
        String formmatedString = "";

        for(int i = 1; i <= 6; i++)
        {
            formmatedString += stateString.charAt(i-1);
            if(i % 3 == 0)
                formmatedString += '\n';
        }

        return formmatedString;
        }
}
