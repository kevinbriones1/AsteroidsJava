/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

//Menu de ayuda, muestra los controles; similar a la opcion del menu 
public class HelpState extends GameState{
    
    private SpriteBatch sb;

    
    private BitmapFont titleFont;
    private BitmapFont font;
    private BitmapFont subTitleFont;
    
    private final String title = "Controles";
    private final String multi= "<- Multiplayer";
    private final String indi="<- Individual";
    
    
    private int currentItem;
    private String[] Info;

    
    public HelpState(GameStateManager gsm){
        super(gsm);
        init();
    }
        public void init(){

    sb = new SpriteBatch();
    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Fuente/Hyperspace Bold.ttf"));
    titleFont = gen.generateFont(60);
    titleFont.setColor(Color.CYAN);
    
    subTitleFont = gen.generateFont(25);
    subTitleFont.setColor(Color.GREEN);
    
    font = gen.generateFont(12);
    
    Info= new String[]{
        "Arriba: UP",
        "Izquierda: LEFT",
        "Derecha: RIGHT",
        "Disparar: SHIFT",
        "---------------",
        "Jugador 1",
        "Arriba: UP",
        "Izquierda: LEFT",
        "Derecha: RIGHT",
        "Disparar: DOWN",
        "Jugador 2",
        "Arriba: W",
        "Izquierda: A",
        "Derecha: D",
        "Disparar: S",
        "Salir al Menu"
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
        titleFont.draw(sb,title,(Game.WIDTH-width)/ 2, 380);
        
        float width2 = subTitleFont.getBounds(multi).width;
        subTitleFont.draw(sb,multi,200, 130);
        float width3 = subTitleFont.getBounds(indi).width;
        subTitleFont.draw(sb,indi,200, 280);
        // Dibujar info
        
        for(int i = 0; i < Info.length; i++){
            width = font.getBounds(Info[i]).width;
            if(currentItem == i) font.setColor(Color.BLUE);
            else font.setColor(Color.WHITE);
            font.draw(sb, Info[i], 50, 315-20*i);
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
        
        if(currentItem == 15){
           gsm.setState(GameStateManager.MENU);
        } 
    }
}
