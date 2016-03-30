/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import javax.xml.bind.ParseConversionEvent;

/**
 *
 * @author a8pin
 */
public class projeto_aed2_2016 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*
         * St's Principais 
         */
        // red-black trees
        RedBlackBST_Projecto<String, Genero> generosST = new RedBlackBST_Projecto<>();
        RedBlackBST_Projecto<String, Musica> musicasST = new RedBlackBST_Projecto<>();
        SeparateChainingHashST1<String, Artista> artistasST = new SeparateChainingHashST1<>();
        SeparateChainingHashST1<String, Utilizador> utilizadoresST = new SeparateChainingHashST1<>();
        /*
         * Inicialização das St's
         */
        loadFromFileGenerosST(generosST, ".//data//generos.txt");
        loadFromFileArtistasST(artistasST, ".//data//artista.txt");
        loadFromFileUtilizadoresST(utilizadoresST, ".//data//pessoas.txt");
        loadFromFileMusicasST(musicasST,generosST, ".//data//musicas.txt");
        /* 
         *  Chamada dos Clientes 
         */
        printMusicByGenres(generosST);

    }

    public static void loadFromFileGenerosST(RedBlackBST_Projecto<String, Genero> generoST, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String genero = texto[0];
            String descricao = texto[1];

            Genero g = new Genero(genero, descricao);
            generoST.put(genero, g);
        }
    }

    public static void loadFromFileMusicasST(RedBlackBST_Projecto<String, Musica> musicasST,RedBlackBST_Projecto<String, Genero> generosSt, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String ISRC = texto[0];
            String nome = texto[1];
            Float duracao = Float.parseFloat(texto[2]);
            String artista = texto[3];
            String genero = texto[4];

            Musica m = new Musica (ISRC, nome, duracao, artista, genero);
            musicasST.put(ISRC, m);
            
            Genero g = generosSt.get(genero);
            g.getGeneroMusicsST().put(ISRC, m);
        }
    }

    public static void loadFromFileArtistasST(SeparateChainingHashST1<String, Artista> artistasST, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String username = texto[0];
            String nome = texto[1];
            String generomusical = texto[2];

            Artista a = new Artista(username, nome, generomusical);
            artistasST.put(username, a);
        }
    }
    
    public static void loadFromFileUtilizadoresST(SeparateChainingHashST1<String, Utilizador> utilizadoresST, String path) {
        In in = new In(path); // abertura do ficheiro/stream de entrada
        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String nome = texto[0];
            String username = texto[1];
            String email = texto[2];

            Utilizador u = new Utilizador(nome, username, email);
            utilizadoresST.put(username, u);
        }
    }
    public static void printMusicByGenres(RedBlackBST_Projecto<String, Genero> genreSt) {
        StdOut.print("\n\nLista de Generos:\n");
        for (String g : genreSt.inOrder()) {
            StdOut.println("Género: " + g);
            
            RedBlackBST_Projecto<String, Musica> musicas = genreSt.get(g).getGeneroMusicsST();
            
            for (String isrc: musicas.keys()) {
                Musica m = (Musica) musicas.get(isrc);
                StdOut.println("    Música: " + m.getNome() + " Artista: " + m.getArtista());
            }
        }
    }

}
