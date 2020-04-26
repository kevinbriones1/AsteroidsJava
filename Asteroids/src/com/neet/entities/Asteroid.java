/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

/**
 *
 * @author Kevin
 */

//Clase de los asteroides 
public class Asteroid extends SpaceObjets{
    
    private int type;
    
    public static final int SMALL=0;
    public static final int MEDIUM=1;
    public static final int LARGE=2;
    
   private int score;
   private int score2; 
    private int numPoints;
    private float[] dists;
    
    private boolean remove;
    
    public Asteroid(float x, float y, int type) {
        
       this.x = x;
       this.y = y;
       this.type = type;
        
       // Le da el punteo, velocidad, tamanio a los diferentes tipos de asteroides 
        if(type== SMALL){
            
         numPoints = 8;
         width = height = 12;
         speed = MathUtils.random(70,100);
         score = 100;   
         score2 = 100;
        }else if (type == MEDIUM){
            
         numPoints = 10;
         width = height = 20;
         speed = MathUtils.random(50,60);
         score = 50;
         score2 = 50;
        }else if(type == LARGE){
            
         numPoints = 12;
         width = height = 40;
         speed = MathUtils.random(20,30);
         score = 20;   
         score2 = 20;
        }
        
        rotationSpeed = MathUtils.random(-1,1);
        radians = MathUtils.random(2*3.1415f);
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
        
        shapex = new float [numPoints];
        shapey = new float [numPoints];
        dists = new float [numPoints];
        
        int radius = width / 2;
        for (int i=0;i<numPoints;i++){
            dists[i] = MathUtils.random(radius/2,radius);
        }
        
        setShape();
    }
    // Le da la forma a los asteroides 
    private void setShape(){
        
        float angle=0;
        for (int i=0;i< numPoints;i++ ){
            shapex[i]= x + MathUtils.cos(angle + radians ) * dists[i];
            shapey[i]= y + MathUtils.sin(angle + radians ) * dists[i];
            angle += 2 * 3.1415f / numPoints;
        }
    }
    
    public int getType(){ return type;}
    
    public boolean ShouldRemove() { return remove; }
    
    public int getScore(){return score;}
    public int getScore2(){return score2;}
    //Actualiza
    public void  update (float dt){
        
        x += dx * dt;
        y += dy * dt;
        
        radians += rotationSpeed * dt;
        setShape();
        
        wrap();
        
    }
    // Dibuja los asteroides 
    public void draw(ShapeRenderer sr){
     
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeType.Line);
        for(int i = 0 , j = shapex.length - 1 ; i< shapex.length; j = i++){
                
                sr.line(shapex[i],shapey[i],shapex[j],shapey[j]);
        
    }
       sr.end();
    }
}
