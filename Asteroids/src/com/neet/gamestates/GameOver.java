/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.neet.main.Game;
import com.neet.managers.GameKeys;
import com.neet.managers.GameStateManager;

/**
 *
 * @author Kevin
 */
//Menu de muerte cuando acaba el juego
public class GameOver extends GameState{
    
    private SpriteBatch sb;
 
    private BitmapFont titleFont;
    private BitmapFont font;
    
     private final String title = "GAME "
             + "OVER ";
     
     private int currentItem;
     private String[] Info;
    
    public GameOver(GameStateManager gsm){
        super(gsm);
        init();
    }
     public void init(){

    sb = new SpriteBatch();
    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Fuente/Hyperspace Bold.ttf"));
    titleFont = gen.generateFont(60);
    titleFont.setColor(Color.RED);
   
    
    font = gen.generateFont(20);
    
    Info= new String[]{
        "Presiona ENTER para continuar"

    };
       
    
    }
      public void update(float dt){
    
        handleInput();
    
    }
      public void draw(){

        
        sb.setProjectionMatrix(Game.cam.combined);
        
        sb.begin();
        //Dibujar Titulo
        float width = titleFont.getBounds(title).width;
        titleFont.draw(sb,title,(Game.WIDTH-width)/ 2, 230);
        

        // Dibujar info
        
        for(int i = 0; i < Info.length; i++){
            width = font.getBounds(Info[i]).width;
            if(currentItem == i) font.setColor(Color.WHITE);
            else font.setColor(Color.WHITE);
            font.draw(sb, Info[i],(Game.WIDTH-width)/ 2 , 30);
        }
        
        
        
        sb.end();
        
        
        
    
    } 
     public void handleInput(){
    
        if(GameKeys.isPressed(GameKeys.UP)){
            if(currentItem > 0){
                currentItem--;
            }
        }
        if(GameKeys.isPressed(GameKeys.DOWN)){
            if(currentItem < Info.length-1){
                currentItem++;
            }
        }
        if(GameKeys.isPressed(GameKeys.ENTER)){
            select();
        }
        
    }
     
     public void dispose(){
     
     }
    
    public void select(){
        
        if(currentItem == 0){
           gsm.setState(GameStateManager.MENU);
        } 
    }
}
