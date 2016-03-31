/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.aed2.project1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Bruno Silva
 */
public class Genero {
    
    private String genero; // chave unica
    private String descricao; // chave unica
    private RedBlackBST_Projecto<String, Musica> generoMusicsSt = new RedBlackBST_Projecto<>(); // musicas deste genero


    public Genero(String genero, String descricao) {
        this.genero = genero;
        this.descricao = descricao;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    /**
     * Get the value of genero
     *
     * @return the value of genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Set the value of genero
     *
     * @param genero new value of genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }


    /**
     * Get the value of generoMusicsSt
     *
     * @return the value of generoMusicsSt
     */
    public RedBlackBST_Projecto getGeneroMusicsST() {
        return generoMusicsSt;
    }

    /**
     * Set the value of generoMusicsSt
     *
     * @param generoMusicsST new value of generoMusicsSt
     */
    public void setGeneroMusicsSt(RedBlackBST_Projecto generoMusicsST) {
        this.generoMusicsSt = generoMusicsST;
    }
    
    public static RedBlackBST_Projecto createGenreSt(RedBlackBST_Projecto generoST) {

        In in = new In(".//data//genero.txt"); // abertura do ficheiro/stream de entrada

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String genero = texto[0];
            String descricao = texto[1];
            
            Genero g = new Genero(genero, descricao);
            generoST.put(genero, g);
        }

        return generoST;

    }
    
    public static RedBlackBST_Projecto editMusic(RedBlackBST_Projecto<String, Musica> musicaST, String key) {
        In in = new In();
        String ISRC = null, nome = null, artista = null, genero = null;
        Float duracao = null;

        Musica m = new Musica(ISRC, nome, duracao, artista, genero);
        m = (Musica) musicaST.get(key);
        StdOut.print("O que pretende editar?");
        StdOut.print("1-ISRC\n");
        StdOut.print("2-Nome\n");
        StdOut.print("3-Duração\n");
        StdOut.print("4-Artista\n");
        StdOut.print("5-Genero\n");
        StdOut.print("0-Sair\n");
        // handle user commands
        boolean quit = false;
        int menuItem;
        //do {
        System.out.print("Escolha uma das opções: ");
        menuItem = in.readInt();
        switch (menuItem) {
            case 1:
                ISRC = StdIn.readString();
                m.setISRC(ISRC);
                musicaST.put(key, null);
                musicaST.put(key, m);
                break;
            case 2:
                nome = StdIn.readString();
                m.setNome(nome);
                musicaST.put(key, null);
                musicaST.put(key, m);
                break;
            case 3:
                duracao = StdIn.readFloat();
                m.setDuracao(duracao);
                musicaST.put(key, null);
                musicaST.put(key, m);
                break;
            case 4:
                artista = StdIn.readString();
                m.setArtista(artista);
                musicaST.put(key, null);
                musicaST.put(key, m);
            case 5:
                genero = StdIn.readString();
                m.setGenero(genero);
                musicaST.put(key, null);
                musicaST.put(key, m);
            case 0:
                quit = true;
                break;
            default:
                System.out.println("Opção inválida");
        }
        //}while(!quit);
        return musicaST;
    }
    
    public static RedBlackBST_Projecto deleteGenreSt(RedBlackBST_Projecto generoST, Integer key)
        {
            generoST.delete(key);
            return generoST;
        }
    
    
}

