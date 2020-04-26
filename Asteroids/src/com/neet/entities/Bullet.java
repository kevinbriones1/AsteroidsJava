/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 *
 * @author Kevin
 */

//Clase de las balas 
public class Bullet extends SpaceObjets{
    
    private float LifeTime;
    private float LifeTimer;
    
    private boolean remove;
    //Genera las balas 
    public Bullet(float x, float y, float radians){
        
        this.x = x;
        this.y = y;
        this.radians = radians;
        
        float speed = 250;
        
        dx = MathUtils.cos(radians)* speed;
        dy = MathUtils.sin(radians)* speed;
        
        width = height = 2;

        
        LifeTimer=0;
        LifeTime=1;
    }
    
    public boolean ShouldRemove() {
        
        return remove;
    }
    //Actualiza
    public void update(float dt){
       
        x += dx * dt;
        y += dy * dt;
        
        wrap();
        
        LifeTimer += dt;
        if(LifeTimer > LifeTime){
            remove = true;
        }
        
    }
    //Dibuja las balas 
    public void draw(ShapeRenderer sr){
       
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Circle);
        sr.circle(x-width/2, y-height/2, width/2);
        sr.end();
    }
}
