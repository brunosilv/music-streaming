/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author bluis
 */
public class Músicas {
        String ISR;
        String name;
        Double durofmus;
        String artist;
        String gender;
        
        
        public Músicas(String ISRC, String titulo, Double durmu,String artista, String genero )
        {
               ISR = ISRC;
               name = titulo;
               durofmus = durmu;
               artist = artista;
               gender = genero;
             
        }
@Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    String NEW_LINE = System.getProperty("line.separator");
    result.append(" Nome: " + name + NEW_LINE );
    result.append(" Duracao: " + durofmus + NEW_LINE );
    result.append(" Artista: " + artist + NEW_LINE );
    result.append(" Genero: " + gender + NEW_LINE );
    return result.toString();
  }  
  
        public static RedBlackBST_Projecto inserirMusicas(RedBlackBST_Projecto RedBlackBST1)
        {
             In in = new In(".//data//musicas.txt");
              while (!in.isEmpty()) 
        {
            String[] texto = in.readLine().split(";");
            String ISRC = texto[0];
            String name = texto[1];
            String durofmus = texto[2];
            String artist = texto[3];
            String gender = texto[4];
            Double duration = Double.parseDouble(durofmus);
            duration = duration * 60;
            
            Músicas musicas = new Músicas(ISRC, name, duration, artist, gender);
            RedBlackBST1.put(ISRC, musicas);
        }
            return RedBlackBST1;
        }
        public static RedBlackBST_Projecto removerMusicas(RedBlackBST_Projecto RedBlackBST1, Integer key)
        {
            RedBlackBST1.delete(key);
            return RedBlackBST1;
        }
        
    public static void main(String[] args)
    {
        String ISRC = null;
        String name = null;
        Double durofmus = null;
        String artist = null;
        String gender = null;
        
        Músicas musicas = new Músicas(ISRC, name, durofmus, artist, gender);
        RedBlackBST_Projecto mt1;
        RedBlackBST_Projecto mt2 = new RedBlackBST_Projecto();
        
        mt1 = musicas.inserirMusicas(mt2);
         for (Object s: mt1.keys()) 
            StdOut.println(s + " " + mt1.get((Comparable) s)); 
    }
        
    }
        
  
   
