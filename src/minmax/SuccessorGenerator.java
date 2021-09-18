/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minmax;

import java.util.HashMap;

/**
 *
 * @author hermano
 */
public interface SuccessorGenerator {
    
   
    public void generateSuccessors(GameState state, Player player);
    
}
