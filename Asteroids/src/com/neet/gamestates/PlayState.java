/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.gamestates;

import com.neet.main.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.neet.entities.Asteroid;
import com.neet.entities.Bullet;
import com.neet.entities.Player;
import java.util.ArrayList;
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
//Clase que crea "El juego" 
public class PlayState extends GameState {
    
    private ShapeRenderer sr;
    private SpriteBatch sb;
    
    private BitmapFont font;
    private Player hudPlayer;
    
    private Player player;
    private ArrayList<Asteroid> Asteroids;
    private ArrayList<Bullet> Bullets;
    
    private int level;
    private int totalAsteroids;
    private int numAsteroidsLeft;
    
    
    public PlayState(GameStateManager gsm){
        super(gsm);
        init();
    }
    
    /**
     *
     */
    @Override
    public void init(){
    
        
    sr = new ShapeRenderer();
    sb = new SpriteBatch();
    
    //Anadir fuente
    FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("Fuente/Hyperspace Bold.ttf"));
    font = gen.generateFont(22);
    
    Bullets =  new ArrayList<Bullet>();   
    player = new Player(Bullets);
    Asteroids = new ArrayList<Asteroid>();
    Asteroids.add(new Asteroid(100,100,Asteroid.LARGE));
    Asteroids.add(new Asteroid(200,100,Asteroid.MEDIUM));
    Asteroids.add(new Asteroid(300,100,Asteroid.SMALL));
    
    level = 1;
    spawnAsteroids();
    
    hudPlayer = new Player(null);
    }
    
    private void spawnAsteroids(){
        
        Asteroids.clear();
        
        int numToSpawn = 5 + level - 1;
        totalAsteroids = numToSpawn * 8;
        numAsteroidsLeft = totalAsteroids;
        
        for(int i=0;i< numToSpawn;i++){
            
            float x = MathUtils.random(Game.WIDTH);
            float y = MathUtils.random(Game.HEIGHT);
            
            float dx = x - player.getx();
            float dy = y - player.gety();
            float dist = (float) Math.sqrt(dx * dx + dy * dy );
            
            while(dist < 150){
                
             x = MathUtils.random(Game.WIDTH);
             y = MathUtils.random(Game.HEIGHT);
            
             dx = x - player.getx();
             dy = y - player.gety();
             dist = (float) Math.sqrt(dx * dx + dy * dy );    
                
            }
            Asteroids.add(new Asteroid(x,y,Asteroid.LARGE));
        }
        
    }
    
    private void splitAsteroids(Asteroid a ){
        numAsteroidsLeft--;
        if(a.getType() == Asteroid.LARGE){
            Asteroids.add(new Asteroid(a.getx(),a.gety(),Asteroid.MEDIUM));
            Asteroids.add(new Asteroid(a.getx(),a.gety(),Asteroid.MEDIUM));
        }
        if(a.getType() == Asteroid.MEDIUM){
            Asteroids.add(new Asteroid(a.getx(),a.gety(),Asteroid.SMALL));
            Asteroids.add(new Asteroid(a.getx(),a.gety(),Asteroid.SMALL));
    }
    }
    @Override
    public void update(float dt){
       // Obtener la entrada del jugador
       handleInput();
       
       // Siguiente Nivel 
       if(Asteroids.size()== 0){
           level ++;
           spawnAsteroids();
       }
       //Actualizar jugador
       player.update(dt);
       if (player.isDead()){
           player.reset();
           player.loseLife();
           if(player.getLives()==0){
               gsm.setState(GameStateManager.GAMEOVER);
           }
           return;
       }
       // Actualizar balas 
       for (int i=0; i < Bullets.size(); i++) {
           Bullets.get(i).update(dt);
           if(Bullets.get(i).ShouldRemove()){
            Bullets.remove(i);
            i--;
           }
       }
       
       //Actualizar Asteroides 
       for (int i=0; i < Asteroids.size(); i++) {
           Asteroids.get(i).update(dt);
           if(Asteroids.get(i).ShouldRemove()){
            Asteroids.remove(i);
            i--;
           }
       }

       
       checkCollisions();
       
    }

    private void checkCollisions(){
       
        //Colision de balas con asteroides
        if(!player.isHit()){
        for (int i=0 ; i < Bullets.size();i++){
            Bullet b = Bullets.get(i);
        for (int j=0 ; j < Asteroids.size();j++){
            Asteroid a = Asteroids.get(j);
            
            if (a.contains(b.getx(),b.gety())){
                Bullets.remove(i);
                i++;
                Asteroids.remove(j);
                j++;
                splitAsteroids(a);
                player.incrementScore(a.getScore());
                break;
            }
        }
        }
        }
        //Colision de jugador con asteroides
        for(int i = 0; i < Asteroids.size();i++){
            Asteroid a = Asteroids.get(i);
            if(a.intersects(player)){
                player.hit();
                Asteroids.remove(i);
                i--;
                splitAsteroids(a);
               
                
                break;
            }
        }
        
    }
    
    @Override
    public void draw(){
        

        sb.setProjectionMatrix(Game.cam.combined);
        sr.setProjectionMatrix(Game.cam.combined);
        
        //Dibujar nave
        player.draw(sr);
    
        
        //Dibujar balas
        for (int i=0; i < Bullets.size(); i++) {
                Bullets.get(i).draw(sr);
        }
        
        
        //Dibujar Asteroides
        for (int i=0; i < Asteroids.size(); i++) {
                Asteroids.get(i).draw(sr);
        }
        
        //Dibujar Marcador
        
        sb.setColor(1,1,1,1);
        sb.begin();
        font.draw(sb,Long.toString(player.getScore()),400,380);
        sb.end();
        
        //Dibujar vidas
        for (int i = 0; i < player.getLives();i++){
            hudPlayer.setPosition(400+ i *10, 350);
            hudPlayer.draw(sr);
        }
        //Dibujar
        
    }

    @Override
    public void handleInput(){
    if(!player.isHit()){
    player.setLeft(GameKeys.isDown(GameKeys.LEFT));
    player.setRight(GameKeys.isDown(GameKeys.RIGHT));
    player.setUp(GameKeys.isDown(GameKeys.UP));
    if (GameKeys.isPressed(GameKeys.SHIFT)){
    player.Shoot();
    }
    }
   
    }

    @Override
    public void dispose(){}
}
