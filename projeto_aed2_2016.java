/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author a8pin
 */
public class project1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*
         * St's Principais 
         */
        // red-black trees
        RedBlackBST_Projecto<String, Genero> generosST = new RedBlackBST_Projecto<>();
  
        /*
         * Inicialização das St's
         */
        loadFromFileGenerosST(generosST, ".//data//aed2_1516//generos.txt");
               
                     
        /* 
         *  Chamada dos Clientes 
         */
     
    }

   
    public static void loadFromFileGenerosST(RedBlackBST_Projecto<String, Genero> generoST, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String genero = texto[0];
            String descricao = texto[1];

            Genero genmusica = new Genero(genero,descricao);
            generoST.put(genero, genmusica);
        }
    }

    public static void printMusicByGenres(RedBlackBST_Projecto<String, Genero> genreSt) {
        StdOut.print("\n\nLista de Generos:\n");
        for (String g : genreSt.inOrder()) {
            StdOut.println("Género: " + g);
            
            /*
            
            RedBlackBST_Projecto<String, Music> musics = genreSt.get(g).getGeneroMusicsST();
            
            for (String isrc: musics.keys()) {
                Music m = (Music) musics.get(isrc);
                StdOut.println("    Música: " + m.getTitulo() + " Artista: " + m.getArtistaName());
            }

            */
        }
    }
    


}