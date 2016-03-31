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
public class Artista {

    String username;
    String nome;
    String generomusical;

    public Artista(String username, String nome, String generomusical) {
        this.username = username;
        this.nome = nome;
        this.generomusical = generomusical;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGeneromusical() {
        return generomusical;
    }

    public void setGeneromusical(String generomusical) {
        this.generomusical = generomusical;
    }

    public static SeparateChainingHashST1 createArtistsSt(SeparateChainingHashST1 artistaST) {

        In in = new In(".//data//artista.txt"); // abertura do ficheiro/stream de entrada

        while (!in.isEmpty()) {
            String[] texto = in.readLine().split(";");
            String username = texto[0];
            String nome = texto[1];
            String generomusical = texto[2];

            Artista a = new Artista(username, nome, generomusical);
            artistaST.put(username, a);
        }

        return artistaST;

    }

    public static SeparateChainingHashST1 updateArtist(SeparateChainingHashST1<String, Artista> artistaST, String key) {
        In in = new In();
        String username = null, nome = null, generomusical = null;

        Artista a = new Artista(username, nome, generomusical);
        a = (Artista) artistaST.get(key);
        StdOut.print("O que pretende editar");
        StdOut.print("1-Nome\n");
        StdOut.print("2-Genero Musica do Artista\n");
        StdOut.print("0-Sair\n");
        // handle user commands
        boolean quit = false;
        int menuItem;
        //do {
        System.out.print("Escolha uma das opções: ");
        menuItem = in.readInt();
        switch (menuItem) {
            case 1:
                nome = StdIn.readString();
                a.setNome(nome);
                artistaST.put(key, null);
                artistaST.put(key, a);
                break;
            case 2:
                generomusical = StdIn.readString();
                a.setGeneromusical(generomusical);
                artistaST.put(key, null);
                artistaST.put(key, a);
                break;
            case 0:
                quit = true;
                break;
            default:
                System.out.println("Opção inválida");
        }
        //}while(!quit);
        return artistaST;
    }

    public static SeparateChainingHashST1 deleteArtistSt(SeparateChainingHashST1 generoST, Integer key) {
        generoST.delete(key);
        return generoST;
    }
}
