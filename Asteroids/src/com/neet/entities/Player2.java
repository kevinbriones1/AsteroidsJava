/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.neet.main.Game;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */

// De la misma manera que el jugador 1 se aplica a el jugador 2 
public class Player2 extends SpaceObjets{
    private final int MAX_BULLETS=6;
    private ArrayList<Bullet> Bullets;
    
    private float[] FuegoX;
    private float[] FuegoY;
    
    private boolean left;
    private boolean right;
    private boolean up;
    
    private float maxSpeed;
    private float acceleration;
    private float deceleration;
    private float aceleracionT;
    
    private boolean hit;
    private boolean dead;
    private float hitTimer;
    private float hitTime;
    
    private Line2D.Float[] hitLines;
    private Point2D.Float[] hitLinesVector;
    
    private long score2;
    private int lives;
    
    public Player2(ArrayList<Bullet>Bullets ){
        
        this.Bullets = Bullets;
        
        x = Game.WIDTH/2;
        y = Game.HEIGHT/2;
        
        maxSpeed=250;
        acceleration = 100;
        deceleration = 10;
        
        shapex = new float[4];
        shapey = new float[4];
        FuegoX = new float[3];
        FuegoY = new float[3];
        
        radians = 3.1415f / 2;
        rotationSpeed = 2;
        
        hit = false;
        hitTimer = 0;
        hitTime = 1;
        
        score2= 0;
        lives = 3;
          
    }
    private void setShape(){
        shapex[0]= x + MathUtils.cos(radians)*8;
        shapey[0]= y + MathUtils.sin(radians)*8;
        
        shapex[1]= x + MathUtils.cos(radians - 4 * 3.1415f /5)*8;
        shapey[1]= y + MathUtils.sin(radians - 4 * 3.1415f /5)*8;
        
        shapex[2]= x + MathUtils.cos(radians + 3.1415f )*5;
        shapey[2]= y + MathUtils.sin(radians + 3.1415f )*5;
        
        shapex[3]= x + MathUtils.cos(radians + 4 * 3.1415f /5)*8;
        shapey[3]= y + MathUtils.sin(radians + 4 * 3.1415f /5)*8;
    }
    
    private void setFlame(){
        
        FuegoX[0]= x + MathUtils.cos(radians - 5 * 3.1415f / 6 )*5;
        FuegoY[0]= y + MathUtils.sin(radians - 5 * 3.1415f / 6 )*5;
        
        FuegoX[1]= x + MathUtils.cos(radians-3.1415f) * (6 + aceleracionT * 50);
        FuegoY[1]= y + MathUtils.sin(radians-3.1415f) * (6 + aceleracionT * 50);
        
        FuegoX[2]= x + MathUtils.cos(radians + 5 * 3.1415f/6)*5;
        FuegoY[2]= y + MathUtils.sin(radians + 5 * 3.1415f/6)*5;
    }
    
    
    public void setLeft(boolean b){ left=b;}
    public void setRight(boolean b){ right=b;}
    public void setUp(boolean b){ up=b;}

    @Override
    public void setPosition(float x, float y){
        
        super.setPosition(x,y);
        setShape();
    }
    
    public boolean isHit(){return hit;}
    public boolean isDead(){return dead;}
    public void reset(){
        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;
        setShape();
        hit = dead = false;
    }
    
    public long getScore2(){return score2;}
    public int getLives(){return lives;}
    public void loseLife(){ lives--;}
    public void incrementScore2(long l){ score2+=l;}
    
    public void Shoot(){
        if(Bullets.size() == MAX_BULLETS) return;
        Bullets.add(new Bullet(x,y,radians));
    }
    
    public void update(float dt){
     
        if(left){
            radians = radians + rotationSpeed * dt;
        } else if(right){
            radians = radians - rotationSpeed * dt;
    }
        
        if(up){
            dx +=  MathUtils.cos(radians)* acceleration * dt;
            dy -=  MathUtils.sin(radians)* acceleration * dt;
            
            aceleracionT += dt;
            if (aceleracionT > 0.1f){
              aceleracionT = 0;  
            } else {
              aceleracionT = 0;
            }
                
        }
        
        float vector = (float) Math.sqrt(dx*dx+dy*dy);
        if (vector>0){
            dx -= (dx/ vector) * deceleration * dt;
            dy -= (dy/ vector) * deceleration * dt;
        }
        if(vector > maxSpeed){
            dx = (dx/ vector) * maxSpeed;
            dy = (dy/ vector) * maxSpeed;
        }
        //posicion
        x += dx * dt;
        y += dy * dt;
        
        setShape();
        
        if(up){
            setFlame();
        }
        
        if(hit){
            hitTimer += dt;
            if (hitTimer > hitTime){
                dead = true;
                hitTimer = 0;
        }
            return; 
        }
        
        wrap();
    }
    
    
    public void draw(ShapeRenderer sr){
        
        sr.setColor(0,0,1,1);
        
        sr.begin(ShapeRenderer.ShapeType.Line);
        // Dibujar Nave
            for(int i = 0 , j = shapex.length - 1 ; i< shapex.length; j = i++){
                
                sr.line(shapex[i],shapey[i],shapex[j],shapey[j]);
        
    }
        // Dibujar Fuego
        if(up){
            for(int i = 0 , j = FuegoX.length - 1 ; i< FuegoX.length; j = i++){
                
                sr.line(FuegoX[i],FuegoY[i],FuegoX[j],FuegoY[j]);
        
    }
          
        }
        sr.end();
    }
    
    public void hit(){
       
        if(hit) return;
        
        hit = true; 
        dx = dy = 0;
        left = up = right = false;
        
        
    }
}
