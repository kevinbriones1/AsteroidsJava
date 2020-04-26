/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neet.managers;

/**
 *
 * @author Kevin
 */
//Clase que asigna las teclas a las distintas funciones 
public class GameKeys {
    
    public static boolean [] keys;
    public static boolean [] pkeys;
    
    private static final int NUM_KEYS = 12 ;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int ENTER = 4;
    public static final int ESCAPE = 5;
    public static final int SPACE = 6;
    public static final int SHIFT = 7;
    public static final int W=8;
    public static final int A=9;
    public static final int S=10;
    public static final int D=11;
    
    static {
        keys = new boolean [NUM_KEYS];
        pkeys = new boolean [NUM_KEYS];
    }
    
    public static void update(){
        for(int i =0; i < NUM_KEYS; i++){
            pkeys[i] = keys[i];
        }
    }
    
    public static void setKey(int k, boolean b){
        keys[k]=b;
    }
    
    public static boolean isDown(int k){
        
       return keys[k]; 
    }
    
    public static boolean isPressed(int k){
        return keys[k] && !pkeys[k];
    }
}
