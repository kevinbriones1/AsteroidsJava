 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevin.main2;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.neet.main.Game;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author Kevin
 */
public class Main {
    
    //Clase principal, en esta se ejecuta el juego
    public static void main(String[] args) throws IOException {

        try{
            // TODO code application logic here

            //Crea el canvas con funciones especiales de la libreria LibGdx
            LwjglApplicationConfiguration cfg = 
                    new LwjglApplicationConfiguration();
            cfg.title="ASTEROIDS";
            cfg.width = 500;
            cfg.height = 400;
            cfg.useGL20 = false;
            cfg.resizable = false;
             new LwjglApplication(new Game(), cfg);
        }catch(Exception ex){
            FileWriter fichero = null;
            PrintWriter pw = null;
            
            fichero = new FileWriter(DirectorioActual()+"/log.txt");
            pw = new PrintWriter(fichero);
            
            pw.append(ex.getMessage());
        }        
    }

    public static String DirectorioActual(){
        String directorio = System.getProperty("java.class.path");
        File dir = new File(directorio);
        String directorioPadre = dir.getParent();
        return directorioPadre;
    }
}