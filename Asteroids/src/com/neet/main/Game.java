/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.main;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.neet.managers.GameInput;
import com.neet.managers.GameKeys;
import com.neet.managers.GameStateManager;
/**
 *
 * @author Kevin
 */
// Clase que genera y organiza la movilidad y funcion 
public class Game implements ApplicationListener{
    
    public static int WIDTH;
    public static int HEIGHT;
    
    public static OrthographicCamera cam;
    
    private GameStateManager gsm;
    
    @Override
    public void create() {
    
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        
        cam = new OrthographicCamera(WIDTH,HEIGHT);
        cam.translate(WIDTH/2, HEIGHT/2);
        cam.update();
        
        Gdx.input.setInputProcessor( 
                new GameInput() 
                 );
       
        gsm = new GameStateManager();
        
}


    @Override
    public void render(){
      
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();
        
        GameKeys.update();
    }
 

    @Override
    public void resize(int WIDTH,int HEIGHT){
        
    }
   

    @Override
    public void pause (){
        
    }
    
    

    @Override
    public void resume(){
        
    } 
    
    
    @Override
    public void dispose(){
        
    }
}
