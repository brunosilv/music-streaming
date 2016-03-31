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
public class Musica {

    String ISRC;
    String nome;
    Float duracao;
    String artista;
    String genero;

    public Musica(String ISRC, String nome, Float duracao, String artista, String genero) {
        this.ISRC = ISRC;
        this.nome = nome;
        this.duracao = duracao;
        this.artista = artista;
        this.genero = genero;
    }

    public String getISR() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getDuracao() {
        return duracao;
    }

    public void setDuracao(Float duracao) {
        this.duracao = duracao;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public static RedBlackBST_Projecto createMusicSt(RedBlackBST_Projecto musicaST) {

        In in = new In(".//data//musicas.txt"); // abertura do ficheiro/stream de entrada

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String ISRC = texto[0];
            String nome = texto[1];
            Float duracao = Float.parseFloat(texto[2]);
            String artista = texto[3];
            String genero = texto[4];

            Musica g = new Musica(ISRC, nome, duracao, artista, genero);
            musicaST.put(genero, g);
        }

        return musicaST;

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

    public static RedBlackBST_Projecto deleteMusicSt(RedBlackBST_Projecto musicaST, Integer key) {
        musicaST.delete(key);
        return musicaST;
    }
}
