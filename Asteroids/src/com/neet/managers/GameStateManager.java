/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.managers;

import com.neet.gamestates.GameOver;
import com.neet.gamestates.GameState;
import com.neet.gamestates.HelpState;
import com.neet.gamestates.MenuState;
import com.neet.gamestates.MultiplayerState;
import com.neet.gamestates.PlayState;

/**
 *
 * @author Kevin
 */
// Clase que permite llamar a el resto de clases como el menu, juego, multiplayer, ayuda, y juego terminado 
public class GameStateManager {
    
    private GameState gameState;
    
    public static final int MENU=0;
    public static final int PLAY=17921792;
    public static final int MULTIPLAYER=2208;
    public static final int HELP=822;
    public static final int GAMEOVER=777;
    
    public GameStateManager(){
        setState(MENU);
    }
   
    public void setState(int state){
        
        if(gameState != null) gameState.dispose();
        if(state == MENU){
        gameState= new MenuState(this);
        }
        if(state == PLAY){
            gameState= new PlayState(this);
        }
        if(state == MULTIPLAYER){
            gameState= new MultiplayerState(this);
        }
        if(state == HELP){
            gameState = new HelpState(this);
        }
        if(state == GAMEOVER ){
            gameState = new GameOver(this);
        }
    }
     
    public void update(float dt){
        gameState.update(dt);
    }
    
    public void draw(){
        gameState.draw();
    }
}
