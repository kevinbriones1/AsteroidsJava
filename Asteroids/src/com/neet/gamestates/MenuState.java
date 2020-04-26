/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.gamestates;

import com.neet.main.Game;
//import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import com.neet.managers.GameKeys;
import com.neet.managers.GameStateManager;

/**
 *
 * @author Kevin
 */
//Clase del menu principal del juego 
public class MenuState extends GameState{
    
    private SpriteBatch sb;
    
    private BitmapFont titleFont;
    private BitmapFont font;
    
    private final String title = "Asteroids";
    
    private int currentItem;
    private String[] menuItems;
    
    public MenuState(GameStateManager gsm){
        super(gsm);
        init();
    }
    
    public void init(){
    // Selecciona el tipo de letra con la funcion especial de la libreria 
    sb = new SpriteBatch();
    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Fuente/Hyperspace Bold.ttf"));
    titleFont = gen.generateFont(60);
    titleFont.setColor(Color.CYAN);
    
    font = gen.generateFont(30);
    
    menuItems= new String[]{
        "Jugar",
        "Cooperativo",
        "Ayuda",
        "Salir"
    };
    }
    @Override
    public void update(float dt){
    
        handleInput();
    
    }
    @Override
    public void draw(){
    
        sb.setProjectionMatrix(Game.cam.combined);
        
        sb.begin();
        //Dibujar Titulo
        float width = titleFont.getBounds(title).width;
        titleFont.draw(sb,title,(Game.WIDTH-width)/ 2, 300);
        
        // Dibujar Menu, Con el array ya disenado el for permite moverse de una seccion a la otra 
        for(int i = 0; i < menuItems.length; i++){
            width = font.getBounds(menuItems[i]).width;
            if(currentItem == i) font.setColor(Color.BLUE);
            else font.setColor(Color.WHITE);
            font.draw(sb, menuItems[i], (Game.WIDTH-width)/2, 180-35*i);
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
            if(currentItem < menuItems.length-1){
                currentItem++;
            }
        }
        if(GameKeys.isPressed(GameKeys.ENTER)){
            select();
        }
        
    }
    public void dispose(){}
    //Si se presiona esta opcion nos permite ingresar a la opcion correspondiente 
    public void select(){
        
        if(currentItem == 0){
           gsm.setState(GameStateManager.PLAY);
        } else if(currentItem == 1){
           gsm.setState(GameStateManager.MULTIPLAYER);
        } else if(currentItem == 2){
           gsm.setState(GameStateManager.HELP);
        } else if (currentItem == 3){
            Gdx.app.exit();
        }
        
    }
}
