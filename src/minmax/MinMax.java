/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax;

import java.util.LinkedList;
import java.util.Random;

import tictactoe.NewGameState;
import tictactoe.TicTacToeGenerator;
import tictactoe.TicTacToeState;

/**
 *
 * @author hermano
 */
public class MinMax {

    public enum GameStyle{OPTIMAL_VS_OPTIMAL, OPTIMAL_VS_RANDOM, RANDOM_VS_RANDOM};
    
    public static void createTree(SuccessorGenerator generator, GameState initialState)
    {
        /*Inicializando a busca MinMax a partir do player Max*/
        LinkedList<GameState> currentLevel = new LinkedList<GameState>();
        currentLevel.add(initialState);
        Player currentPlayer = Player.PLAYER_MAX;
        
        int level = 0;
        while(true)
        {
            LinkedList<GameState> nextLevel = new LinkedList<GameState>();
            
            /*Gerando todas as ações possíveis para o nível atual.*/
            for(GameState state : currentLevel)
            {
                generator.generateSuccessors(state, currentPlayer);
                
                for(int i = 0; i < state.getChildren().size(); i++)
                {
                    GameState successorState = state.getChildren().get(i);
                    nextLevel.add(successorState);
                }
            }
            System.out.println("Expandindo nível "+(level++)+" com "+nextLevel.size()+" estados.");
            
            /*Alternando jogadores*/
            currentPlayer = (currentPlayer == Player.PLAYER_MAX)?
                                              Player.PLAYER_MIN:
                                              Player.PLAYER_MAX; 
            
            /*Busca termina quando todos os estados foram explorados*/
            if(nextLevel.isEmpty()) break;
            
            currentLevel.clear();
            currentLevel.addAll(nextLevel);
        }
        
    }
    
    public static void fillEvaluations(GameState state, Player player)
    { 
       Player nextPlayer = (player == Player.PLAYER_MAX)?
                                      Player.PLAYER_MIN:
                                      Player.PLAYER_MAX; 
       
       double minEvaluation = Double.MAX_VALUE;
       double maxEvaluation = Double.MIN_VALUE;
       
       for(GameState successor : state.getChildren())
       {
           if(!successor.isFinalGameState())
           {
               fillEvaluations(successor, nextPlayer);
           }
           
           if(minEvaluation > successor.getEvaluation())
               minEvaluation = successor.getEvaluation();
           
           if(maxEvaluation < successor.getEvaluation())
               maxEvaluation = successor.getEvaluation();
       }
       
       if(player == Player.PLAYER_MAX)
           state.setEvaluation(maxEvaluation);
       else
           state.setEvaluation(minEvaluation);
       
    }
    
    public static GameState playGame(GameState initialState, GameStyle style)
    {
        Random rn = new Random();
        
        /*O jogo começa em um estado inicial*/
        GameState currentState = initialState;
        Player currentPlayer = Player.PLAYER_MAX;
         
        /*Enquanto estado final do jogo não é atingido.*/
        while(!currentState.isFinalGameState())
        {            
            if(currentState.getChildren().isEmpty())
            {
                System.out.println("Erro: Estado sem filhos não avaliado como final.");
                System.out.println(currentState.toFormattedString());
                break;
            }
            
            if(currentPlayer == Player.PLAYER_MAX)
            {
                if(style == GameStyle.OPTIMAL_VS_OPTIMAL 
                    || style == GameStyle.OPTIMAL_VS_RANDOM)
                {
                    double maxEvaluation = Double.NEGATIVE_INFINITY;
                    GameState nextState  = null;
                    
                    for(GameState state : currentState.getChildren())
                    {
                        if(maxEvaluation < state.getEvaluation())
                        {
                            nextState = state;
                            maxEvaluation = state.getEvaluation();
                        }
                    }
                    
                    currentState = nextState;
                }
                else
                {
                    int count = 0;
                    int num = rn.nextInt(currentState.getChildren().size());
                    
                    for(GameState state : currentState.getChildren())
                    {
                        if(count == num)
                        {
                            currentState = state;
                            break;
                        }
                        count++;
                    }
                }
                
                System.out.println("\nMax jogou "+currentState.getEvaluation()+"\n");
                System.out.println(currentState.toFormattedString());
                  
            }
            else
            {
                if(style == GameStyle.OPTIMAL_VS_OPTIMAL)
                {
                    double minEvaluation = Double.POSITIVE_INFINITY;
                    GameState nextState  = null;
                    
                    for(GameState state : currentState.getChildren())
                    {
                        //System.out.println("Avaliacoes min "+state.getEvaluation());
                        if(minEvaluation > state.getEvaluation())
                        {
                            nextState = state;
                            minEvaluation = state.getEvaluation();
                        }
                    }
                    
                    currentState = nextState;
                }
                else
                {
                    int count = 0;
                    int num = rn.nextInt(currentState.getChildren().size());
                    
                    for(GameState state : currentState.getChildren())
                    {
                        if(count == num)
                        {
                            currentState = state;
                            break;
                        }
                        count++;
                    }
                }
                
                System.out.println("\nMin jogou "+currentState.getEvaluation()+"\n");
                System.out.println(currentState.toFormattedString());
            }
            
            /*Alternando jogadores*/
            currentPlayer = (currentPlayer == Player.PLAYER_MAX)?
                                              Player.PLAYER_MIN:
                                              Player.PLAYER_MAX; 
        }
        
        return currentState;
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        /*SuccessorGenerator generator = new TicTacToeGenerator();
        GameState initialState = (new TicTacToeState("")).getInitialState();
        GameState finalState = null;
        
        createTree(generator, initialState);
        fillEvaluations(initialState, Player.PLAYER_MAX);
        finalState = playGame(initialState, GameStyle.RANDOM_VS_RANDOM);
        
        System.out.println("\nO jogo terminou no seguinte estado:\n");
        System.out.println(finalState.toFormattedString());
        System.out.println("\nResultado: "+Double.toString(finalState.getEvaluation()));
        */
        //-------------------------------------------------------------------------------//

        SuccessorGenerator successorGenerator = new TicTacToeGenerator();
        GameState initialState = (new NewGameState("")).getInitialState();
        GameState finalState = null;

        createTree(successorGenerator, initialState);
        fillEvaluations(initialState, Player.PLAYER_MAX);
        finalState = playGame(initialState, GameStyle.RANDOM_VS_RANDOM);

        System.out.println("\nO jogo terminou no seguinte estado:\n");
        System.out.println(finalState.toFormattedString());
        System.out.println("\nResultado: "+Double.toString(finalState.getEvaluation()));
    }
    
}
