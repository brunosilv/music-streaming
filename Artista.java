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
    private RedBlackBST_Projecto<String, Musica> artistMusicSt = new RedBlackBST_Projecto<>();

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

    public RedBlackBST_Projecto<String, Musica> getArtistMusicSt() {
        return artistMusicSt;
    }

    public void setArtistMusicSt(RedBlackBST_Projecto<String, Musica> artistMusicSt) {
        this.artistMusicSt = artistMusicSt;
    }

}
