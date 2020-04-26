/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

/**
 *
 * @author Kevin
 */
// Clase que acepta los valores del teclado 
public class GameInput extends InputAdapter{
    
    @Override
    public boolean keyDown(int k){
        
       if(k == Keys.UP){
           GameKeys.setKey(GameKeys.UP, true);
       }
       if(k == Keys.LEFT){
           GameKeys.setKey(GameKeys.LEFT, true);
       }
       if(k == Keys.DOWN){
           GameKeys.setKey(GameKeys.DOWN, true);
       }
       if(k == Keys.RIGHT){
           GameKeys.setKey(GameKeys.RIGHT, true);
       }
        if(k == Keys.ENTER){
           GameKeys.setKey(GameKeys.ENTER, true);
       }
         if(k == Keys.ESCAPE){
           GameKeys.setKey(GameKeys.ESCAPE, true);
       }
        if(k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT){
           GameKeys.setKey(GameKeys.SHIFT, true);
       }
        if(k == Keys.W){
            GameKeys.setKey(GameKeys.W, true);
        }
        if(k == Keys.A){
            GameKeys.setKey(GameKeys.A, true);
        }
        if(k == Keys.S){
            GameKeys.setKey(GameKeys.S, true);
        }
        if(k == Keys.D){
            GameKeys.setKey(GameKeys.D, true);
        }
        return true;
    }
    @Override
    public boolean keyUp(int k){
       
        if(k == Keys.UP){
           GameKeys.setKey(GameKeys.UP, false);
       }
       if(k == Keys.LEFT){
           GameKeys.setKey(GameKeys.LEFT, false);
       }
       if(k == Keys.DOWN){
           GameKeys.setKey(GameKeys.DOWN, false);
       }
       if(k == Keys.RIGHT){
           GameKeys.setKey(GameKeys.RIGHT, false);
       }
        if(k == Keys.ENTER){
           GameKeys.setKey(GameKeys.ENTER, false);
       }
         if(k == Keys.ESCAPE){
           GameKeys.setKey(GameKeys.ESCAPE, false);
       }
        if(k == Keys.SHIFT_LEFT || k == Keys.SHIFT_RIGHT){
           GameKeys.setKey(GameKeys.SHIFT, false);
       }
       if(k == Keys.W){
            GameKeys.setKey(GameKeys.W, false);
        }
        if(k == Keys.A){
            GameKeys.setKey(GameKeys.A, false);
        }
        if(k == Keys.S){
            GameKeys.setKey(GameKeys.S, false);
        }
        if(k == Keys.D){
            GameKeys.setKey(GameKeys.D, false);
        } 
        return true;
    }
}
